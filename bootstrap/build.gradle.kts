plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.spring") version "1.9"
} 

dependencies { 
    implementation(project(":application"))  
    implementation(project(':model'))

    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.1"))
    implementation('org.springframework.boot:spring-boot-starter-web')
    
    testImplementation(kotlin("test"))
}