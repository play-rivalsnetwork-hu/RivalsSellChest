import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "hu.rivalsnetwork"
version = "0.0.1-BETA"

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    repositories {
        mavenCentral()

        // Paper
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://oss.sonatype.org/content/repositories/snapshots")

        // Jitpack
        maven("https://jitpack.io")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
    }
}

dependencies {
    implementation(project(path = ":common"))
    implementation(project(path = ":v1_19_R2", configuration = "reobf"))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    processResources {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        dependsOn(":v1_19_R2:reobfJar")

        relocate("dev.dejvokep.boostedyaml", "hu.rivalsnetwork.rivalssellchest.boostedyaml")
        mergeServiceFiles()
    }

    build {
        dependsOn(shadowJar)
    }
}

bukkit {
    name = "RivalsSellChest"
    version = "${project.version}"
    main = "hu.rivalsnetwork.rivalssellchest.RivalsSellChestPlugin"
    apiVersion = "1.19"
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    authors = listOf("Karcsi", "BenceX100")
    description = "SellChest plugin with performance in-mind."
    softDepend = listOf("ShopGUIPlus", "Vault")

    commands {
        register("sellchest")
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}
