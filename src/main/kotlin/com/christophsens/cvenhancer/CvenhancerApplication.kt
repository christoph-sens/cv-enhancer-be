package com.christophsens.cvenhancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CvenhancerApplication

fun main(args: Array<String>) {
    runApplication<CvenhancerApplication>(*args)
}