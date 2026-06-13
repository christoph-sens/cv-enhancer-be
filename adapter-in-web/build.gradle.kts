plugins {
    kotlin("jvm")
} 

dependencies { 
    implementation(project(":bootstrap"))  
    implementation('org.springdoc:springdoc-openapi-starter-webmvc-ui')

    testImplementation(kotlin("test"))
}