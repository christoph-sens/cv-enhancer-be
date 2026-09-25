plugins {
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

// The dependency-management plugin enforces the Boot BOM and would override the root-level Tomcat pin.
extra["tomcat.version"] = libs.versions.tomcat.get()

dependencies {
    implementation(project(":model"))
    implementation(project(":application"))
    implementation(project(":adapter-in-web"))
    implementation(project(":adapter-out"))

    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.webflux)

    testImplementation(libs.spring.boot.starter.webflux.test)
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    archiveFileName.set("cv-enhancer-be.jar")
}
