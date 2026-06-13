package com.christophsens.cvenhancer.service

import com.christophsens.cvenhancer.dto.CvVacancyDto
import com.christophsens.cvenhancer.prompt.CompareVacancyPrompt
import com.christophsens.cvenhancer.prompt.CreateCoverLetterPrompt
import com.christophsens.cvenhancer.prompt.ImprovePrompt
import com.christophsens.cvenhancer.prompt.TranslationPrompt
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.stereotype.Service

@Service
class CvService(
    private val chatModel: ChatModel
) {

    fun translateCv(cv: String, languageCode: String, countryCode: String): String {
        val response = chatModel.call(TranslationPrompt.getPrompt(cv, languageCode, countryCode))
        return extractContent(response)
    }

    fun improveCv(cv: String): String {
        val response = chatModel.call(ImprovePrompt.getPrompt(cv))
        return extractContent(response)
    }

    fun compareCvAndVacancay(dto: CvVacancyDto): String {
        val response = chatModel.call(CompareVacancyPrompt.getPrompt(dto))
        return extractContent(response)
    }

    fun createCoverLetter(dto: CvVacancyDto): String {
        val response = chatModel.call(CreateCoverLetterPrompt.getPrompt(dto))
        return extractContent(response)
    }

    private fun extractContent(response: ChatResponse): String {
        return response.result.output.text ?: ""
    }
}