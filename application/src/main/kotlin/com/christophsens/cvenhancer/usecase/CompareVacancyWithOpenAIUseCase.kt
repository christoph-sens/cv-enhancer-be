package com.christophsens.cvenhancer.usecase

import com.christophsens.cvenhancer.application.port.`in`.CompareVacancyPort
import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort

/**
 * Use Case for comparing a CV with a vacancy.
 */
class CompareVacancyWithOpenAIUseCase(
    private val openAiChatPort: OpenAiChatPort
): CompareVacancyPort {

    override fun compare(cvContent: String, vacancyContent: String): String {
        val prompt = buildComparePrompt(cvContent, vacancyContent)
        val response = openAiChatPort.call(prompt)
        return response
    }

    private fun buildComparePrompt(
        cvContent: String,
        vacancyContent: String
    ): String {
        return """
            This is the CV: "$cvContent". This is the vacancy:"$vacancyContent".
            Give feedback how to improve the CV according to the vacancy.
            Your response must be in the language of the CV.
        """.trimIndent()
    }
}