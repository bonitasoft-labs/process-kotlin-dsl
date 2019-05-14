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

## Usage and exemples TBD
