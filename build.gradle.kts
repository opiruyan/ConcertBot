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
