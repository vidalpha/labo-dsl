// The pluginManagement block must appear before any other statements in the script.
pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    includeBuild("gradle/plugins")
}

rootProject.name = "DSL"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("mapstruct-test")
include("annotation-processor")
include("annotation-user")
