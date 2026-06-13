package com.christophsens.cvenhancer.service

import com.christophsens.cvenhancer.dto.CvVacancyDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.model.Generation
import org.springframework.ai.chat.prompt.Prompt

class CvServiceTest {

    @Mock
    private lateinit var chatModel: ChatModel

    @InjectMocks
    private lateinit var cvService: CvService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    private fun createMockChatResponse(content: String): ChatResponse {
        val generation = Generation(AssistantMessage(content))
        return ChatResponse(listOf(generation))
    }

    @Test
    fun testTranslateCv() {
        val cv = "Sample CV"
        val languageCode = "en"
        val countryCode = "US"
        val expectedResponse = "Translated CV"

        `when`(chatModel.call(any(Prompt::class.java))).thenReturn(createMockChatResponse(expectedResponse))

        val result = cvService.translateCv(cv, languageCode, countryCode)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun testImproveCv() {
        val cv = "Sample CV"
        val expectedResponse = "Improved CV"

        `when`(chatModel.call(any(Prompt::class.java))).thenReturn(createMockChatResponse(expectedResponse))

        val result = cvService.improveCv(cv)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun testCompareCvAndVacancay() {
        val dto = CvVacancyDto("cv", "vacancy")
        val expectedResponse = "Comparison Result"

        `when`(chatModel.call(any(Prompt::class.java))).thenReturn(createMockChatResponse(expectedResponse))

        val result = cvService.compareCvAndVacancay(dto)

        assertEquals(expectedResponse, result)
    }

    @Test
    fun testCreateCoverLetter() {
        val dto = CvVacancyDto("cv", "vacancy")
        val expectedResponse = "Cover Letter"

        `when`(chatModel.call(any(Prompt::class.java))).thenReturn(createMockChatResponse(expectedResponse))

        val result = cvService.createCoverLetter(dto)

        assertEquals(expectedResponse, result)
    }
}