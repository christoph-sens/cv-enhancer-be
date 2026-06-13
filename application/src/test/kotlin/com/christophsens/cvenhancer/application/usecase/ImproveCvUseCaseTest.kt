package com.christophsens.cvenhancer.application.usecase

import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import com.christophsens.cvenhancer.usecase.ImproveCvUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ImproveCvUseCaseTest {

    private val openAiChatPort = mockk<OpenAiChatPort>()
    private val useCase = ImproveCvUseCase(openAiChatPort)

    @Test
    fun `should improve cv and return open ai response`() {
        val cvContent = "I am a backend developer."
        val expectedPrompt = """
            This is the CV: "$cvContent".
            Give feedback on how to improve it.
            Your response must be in the language of the CV.
        """.trimIndent()
        val expectedResponse = "Consider adding more details about your Kotlin experience."

        every { openAiChatPort.call(expectedPrompt) } returns expectedResponse

        val result = useCase.improve(cvContent)

        assertThat(result).isEqualTo(expectedResponse)
        verify(exactly = 1) { openAiChatPort.call(expectedPrompt) }
    }
}