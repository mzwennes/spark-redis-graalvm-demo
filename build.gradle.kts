import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    id("com.github.johnrengelman.shadow") version "2.0.4"
}

group = "nl.debijenkorf"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("com.sparkjava:spark-kotlin:1.0.0-alpha")
    compile("redis.clients:jedis:2.9.0")
    compile("org.slf4j:slf4j-api:1.7.25")
    compile("org.slf4j:slf4j-log4j12:1.7.25")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    task("fatJar", type = Jar::class) {
        baseName = "${project.name}-all-deps"
        manifest {
            attributes(mapOf(
                "Main-Class" to "inventory.ApplicationKt"
            ))
        }
        from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })
        with(tasks["jar"] as CopySpec)
    }
}