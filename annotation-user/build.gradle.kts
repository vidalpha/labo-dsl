plugins {
    id("my-application") }


application {
    mainClass.set("com.lab.example.Application")
}


dependencies {
    annotationProcessor(project(":annotation-processor"))
    implementation(project(":annotation-processor"))
}