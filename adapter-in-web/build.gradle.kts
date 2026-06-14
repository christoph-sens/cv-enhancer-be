plugins {
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(project(":application"))
    implementation(project(":model"))

    implementation(platform(libs.spring.boot.bom))
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.springdoc.webflux.ui)
}
