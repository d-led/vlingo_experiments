plugins {
    java
    application
}

application {
    mainClassName = "dled.github.App"
}

dependencies {
    compile("com.google.guava:guava:23.0")
    compile("io.appulse.encon:encon:1.5.0")
    compile("io.vlingo:vlingo-actors:0.6.1")

    testCompile("junit:junit:4.12")
}

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    maven( "https://repo1.maven.org/maven2/")
}
