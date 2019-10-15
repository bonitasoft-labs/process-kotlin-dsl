# A DSL to create processes


```kotlin
process("Request Loan", "1.0") {
        val requester = initiator("requester")
        val validator = actor("validator")
        data {
            text named "type"
            integer named "amount"
            boolean named "accepted"
            text named "reason"
        }
        contract {
            text named "type" withDescription "type of the loan"
            integer named "amount" withDescription "amount of the loan"
        }
        val review = userTask("Review request") {
            actor = validator
            contract {
                boolean named "accept" withDescription "whether the load is accepted or not"
                text named "reason" withDescription "why the loan was accepted/rejected"
            }
        }
        [...]
```

## Build

Build and install to your local maven repository using

```
./gradlew publishToMavenLocal
```

## Usage

Add a dependency on this DSL

gradle
```groovy
dependencies {
    implementation("org.bonitasoft.engine.dsl:process-kotlin-dsl:0.0.1")
}
```

maven
```xml
<dependency>
    <groupId>org.bonitasoft.engine.dsl</groupId>
    <artifactId>process-kotlin-dsl</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Example


Start building a process using `org.bonitasoft.engine.dsl.process.DslKt.process`

Then build your processes using the DSL
e.g.
```kotlin
 val process = process("MyProcess", "1.0") {
            automaticTask("Step1")
            automaticTask("Step2")
            automaticTask("Step3")
            transitions {
                "Step1" to "Step2"
                "Step2" to "Step3"
            }
        }
```

You can then retrieve the BusinessArchive using
```
val businessArchive = process.export()
```