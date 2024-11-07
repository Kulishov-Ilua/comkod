import org.jetbrains.kotlin.js.naming.encodeSignature

plugins {
    kotlin("jvm") version "1.9.23"
    id("org.graalvm.buildtools.native") version "0.10.3"  // Плагин для GraalVM
}

group = "ru.KulishovIV"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xencoding=UTF-8", "-jvm-target=21")
    }
}


kotlin {
    jvmToolchain(21)
}

graalvmNative {
    binaries {
        named("main") {
            mainClass.set("ru.KulishovIV.MainKt")  // Укажите ваш основной класс
            buildArgs.add("-Dfile.encoding=UTF-8")
            buildArgs.add("--initialize-at-build-time=java.nio.charset")
            //outputName.set("my-application")    // Название итогового exe


        }
    }



}