plugins {
    java
    application
}

application {
    mainClassName = "dled.github.App"
}

dependencies {
    compile("io.appulse.encon:encon:1.6.7")
    compile("io.vlingo:vlingo-actors:0.7.1")

    testCompile("junit:junit:4.12")
}

repositories {
    jcenter()
    mavenLocal()
    mavenCentral()
    maven( "https://repo1.maven.org/maven2/")
}
