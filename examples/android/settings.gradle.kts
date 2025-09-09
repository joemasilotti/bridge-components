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

includeBuild("/Users/joemasilotti/workspace/projects/bridge-components/free/android") {
    dependencySubstitution {
        substitute(module("com.masilotti.bridgecomponents:core"))
            .using(project(":core"))
    }
}

rootProject.name = "Demo"
include(":app")
 