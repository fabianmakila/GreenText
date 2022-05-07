plugins {
    id("net.kyori.indra") version "2.1.1"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    java
}

group = "fi.fabianadrian"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
}

indra {
    javaVersions().target(17)
}

bukkit {
    main = "fi.fabianadrian.greentext.GreenText"
    name = rootProject.name
    apiVersion = "1.18"
    author = "FabianAdrian"
    permissions {
        register("greentext.use") {
            description = "Allows the use of greentext."
        }
    }
}