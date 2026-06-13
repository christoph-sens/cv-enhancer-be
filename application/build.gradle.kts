plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":model"))

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.14.2")
    testImplementation("org.assertj:assertj-core:3.27.3")
}