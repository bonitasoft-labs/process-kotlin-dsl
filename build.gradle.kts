import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    id("maven-publish")
}

group = "org.bonitasoft.engine.dsl"
version = "0.0.1"


repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenLocal()
    jcenter()
    maven("http://repositories.rd.lan/maven/all/")
}

dependencies {
    api("org.bonitasoft.engine:bonita-client:7.9.0.W14-03")
    api("org.bonitasoft.engine:bonita-common:7.9.0.W14-03")
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")



    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.1")
    testImplementation("com.winterbe:expekt:0.5.0")
    testImplementation("org.bonitasoft.engine:bonita-test-api:7.8.3")
    testImplementation("org.bonitasoft.engine:bonita-server:7.8.3")
    testImplementation("org.bonitasoft.platform:platform-resources:7.8.3")
    testImplementation("org.awaitility:awaitility-kotlin:3.1.6")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.1")

    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect")
}

val test by tasks.getting(Test::class) {
    include("**/*Test.class")
    useJUnitPlatform {
        includeEngines("spek2")
    }
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
}