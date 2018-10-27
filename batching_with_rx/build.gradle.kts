import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    kotlin("jvm") version "1.2.61"
}

application {
    mainClassName = "dled.github.App"
}

dependencies {
    compile("com.google.guava:guava:23.0")

    compile("io.vlingo:vlingo-actors:0.7.1")

    compile("io.reactivex.rxjava2:rxjava:2.2.2")
    compile("io.reactivex.rxjava2:rxkotlin:2.2.0")


    testCompile("junit:junit:4.12")

    compile(kotlin("stdlib-jdk8"))
}

repositories {
    jcenter()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}