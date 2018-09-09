plugins {
    java
    application
}

application {
    mainClassName = "dled.github.App"
}

dependencies {
    compile("com.google.guava:guava:23.0")
    compile("io.vlingo:vlingo-actors:0.6.1")

    testCompile("junit:junit:4.12")
}

repositories {
    jcenter()
}
