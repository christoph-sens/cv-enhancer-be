package com.christophsens.cvenhancer.application.usecase

import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import com.christophsens.cvenhancer.usecase.CompareVacancyWithOpenAIUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CompareVacancyWithOpenAIUseCaseTest {

    private val openAiChatPort = mockk<OpenAiChatPort>()
    private val useCase = CompareVacancyWithOpenAIUseCase(openAiChatPort)

    @Test
    fun `should compare cv with vacancy and return open ai response`() {
        val cvContent = "I am a backend developer."
        val vacancyContent = "We are looking for a Kotlin developer."
        val expectedPrompt = """
            This is the CV: "$cvContent". This is the vacancy:"$vacancyContent".
            Give feedback how to improve the CV according to the vacancy.
            Your response must be in the language of the CV.
        """.trimIndent()
        val expectedResponse = "Improve your Kotlin experience section."

        every { openAiChatPort.call(expectedPrompt) } returns expectedResponse

        val result = useCase.compare(cvContent, vacancyContent)

        assertThat(result).isEqualTo(expectedResponse)
        verify(exactly = 1) { openAiChatPort.call(expectedPrompt) }
    }
}