plugins {
    id("application")
}

application {
    mainClass.set("Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "Main"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("org.telegram:telegrambots:6.0.1")
}