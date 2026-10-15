pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hello-word-two"
include(":app")
include(":studyanroidlog")
include(":studyimage")
include(":studydependencies")
include(":studyimagebotton")
include(":studyprogress")
include(":studyseekbar")
include(":studyradiogroup")
include(":studyswitch")
include(":studyscrollview")
include(":studydatetime")
include(":studydateday")
include(":studycalendar")
include(":studylistview")
include(":studylistviewdata")
