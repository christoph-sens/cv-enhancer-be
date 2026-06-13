plugins { 
    kotlin("jvm")   
} 

dependencies {
    implementation(project(":application"))
    implementation('org.springframework.boot:spring-boot-starter')
    
    testImplementation(kotlin("test"))
}