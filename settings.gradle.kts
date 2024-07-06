pluginManagement {
    repositories {
        google()
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

rootProject.name = "FakeStoreProduct"
include(":app")
include(":util")
include(":feature")
include(":data")
include(":core")
include(":core:data")
include(":core:data")
include(":core:network")
include(":core:database")
include(":core:domain")
include(":core:common")
include(":core:ui")
