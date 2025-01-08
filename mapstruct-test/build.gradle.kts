// Example tiré de https://github.com/tkgregory/gradle-annotation-processor-example
// Pour tester faire: > .\gradlew :mapstruct-test:compileJava --console=plain

plugins {
    id("my-java-base")
}

dependencies {
    // Enable annotation processing in IDE settings with generated directories
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.mapstruct:mapstruct:1.6.3")
}


tasks.withType<JavaCompile>().configureEach {
    doFirst {
        // Pour tester que mapstruct-processor est bien pris en charge
        println("AnnotationProcessorPath for ${name} is ${options.annotationProcessorPath?.files}")
    }
}