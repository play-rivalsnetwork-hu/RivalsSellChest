plugins {
    id("java")
}

dependencies {
    compileOnly("com.github.decentsoftware-eu:decentholograms:2.7.11")
    implementation("dev.dejvokep:boosted-yaml:1.3.1")
    compileOnly("com.github.brcdev-minecraft:shopgui-api:3.0.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}