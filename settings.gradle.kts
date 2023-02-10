pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
rootProject.name = "RivalsSellChest"

include(
    "common",
    "v1_19_R2"
)