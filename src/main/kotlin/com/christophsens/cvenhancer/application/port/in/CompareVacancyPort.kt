package com.christophsens.cvenhancer.application.port.`in`

/**
 * Input port for comparing a CV with a vacancy.
 */
interface CompareVacancyPort {
    fun compare(cvContent: String, vacancyContent: String): String
}