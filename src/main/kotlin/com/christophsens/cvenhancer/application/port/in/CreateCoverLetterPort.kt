package com.christophsens.cvenhancer.application.port.`in`

/**
 * Input port for creating a cover letter.
 */
interface CreateCoverLetterPort {
    fun create(cvContent: String, vacancyContent: String): String
}