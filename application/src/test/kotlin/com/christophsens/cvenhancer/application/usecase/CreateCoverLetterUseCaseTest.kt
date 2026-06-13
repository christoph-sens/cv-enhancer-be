package com.christophsens.cvenhancer.application.usecase

import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import com.christophsens.cvenhancer.usecase.CreateCoverLetterUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateCoverLetterUseCaseTest {

    private val openAiChatPort = mockk<OpenAiChatPort>()
    private val useCase = CreateCoverLetterUseCase(openAiChatPort)

    @Test
    fun `should create cover letter and return open ai response`() {
        val cvContent = "I am a backend developer."
        val vacancyContent = "We are looking for a Kotlin developer."
        val expectedPrompt = """
            This is the CV: "$cvContent". This is the vacancy:"$vacancyContent".
            Create a cover letter for my application.
            Your response must be in the language of the CV.
        """.trimIndent()
        val expectedResponse = "Dear hiring team, I am excited to apply for this Kotlin developer position."

        every { openAiChatPort.call(expectedPrompt) } returns expectedResponse

        val result = useCase.create(cvContent, vacancyContent)

        assertThat(result).isEqualTo(expectedResponse)
        verify(exactly = 1) { openAiChatPort.call(expectedPrompt) }
    }
}