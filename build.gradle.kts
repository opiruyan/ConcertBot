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

tasks.register<Jar>("fatJar") {
    group = "Custom Tasks"
    description = "Create a single JAR with all dependencies"

    archiveClassifier.set("fat")
    from(sourceSets["main"].output)

    manifest {
        attributes["Main-Class"] = "Main"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    doLast {
        println("Successfully created fat jar")
    }
}
