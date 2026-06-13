package com.christophsens.cvenhancer.application.port.`in`

/**
 * Input port for improving a CV.
 */
interface ImproveCvPort {
    fun improve(cvContent: String): String
}