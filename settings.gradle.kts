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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieDB"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core:common")
include(":features:domain")
include(":features:data")
include(":features:presentation:movielist")
include(":features:presentation:moviedetails")
include(":core:network")
include(":core:navigation")
include(":core:designsystem")
