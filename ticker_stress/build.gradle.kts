/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java project to get you started.
 * For more details take a look at the Java Quickstart chapter in the Gradle
 * user guide available at https://docs.gradle.org/4.10/userguide/tutorial_java_projects.html
 */

plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building an application
    application
}

application {

    // Define the main class for the application
    mainClassName = "App"
}

dependencies {
    compile("com.google.guava:guava:23.0")
    compile("io.vlingo:vlingo-actors:0.6.1")

    // Use JUnit test framework
    testCompile("junit:junit:4.12")
}

// In this section you declare where to find the dependencies of your project
repositories {
    jcenter()
}
