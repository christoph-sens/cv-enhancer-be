plugins {
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    implementation(project(":application"))
    implementation(project(":model"))

    implementation(platform(libs.spring.boot.bom))
    implementation(libs.spring.context)

    implementation(platform(libs.spring.ai.bom))
    implementation(libs.spring.ai.starter.openai)
}
