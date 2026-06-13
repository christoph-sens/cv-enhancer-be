package com.christophsens.cvenhancer.usecase

import com.christophsens.cvenhancer.application.port.`in`.CreateCoverLetterPort
import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort

/**
 * Use Case for creating a cover letter.
 */
class CreateCoverLetterUseCase(
    private val openAiChatPort: OpenAiChatPort
): CreateCoverLetterPort {

    override fun create(cvContent: String, vacancyContent: String): String {
        val prompt = buildCreateCoverLetterPrompt(cvContent, vacancyContent)
        val response = openAiChatPort.call(prompt)
        return response
    }

    private fun buildCreateCoverLetterPrompt(
        cvContent: String,
        vacancyContent: String
    ): String {
        return """
            This is the CV: "$cvContent". This is the vacancy:"$vacancyContent".
            Create a cover letter for my application.
            Your response must be in the language of the CV.
        """.trimIndent()
    }
}