package com.christophsens.cvenhancer

import com.christophsens.cvenhancer.application.port.`in`.CompareVacancyPort
import com.christophsens.cvenhancer.application.port.`in`.CreateCoverLetterPort
import com.christophsens.cvenhancer.application.port.`in`.ImproveCvPort
import com.christophsens.cvenhancer.application.port.`in`.TranslateCvPort
import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import com.christophsens.cvenhancer.usecase.CompareVacancyWithOpenAIUseCase
import com.christophsens.cvenhancer.usecase.CreateCoverLetterUseCase
import com.christophsens.cvenhancer.usecase.ImproveCvUseCase
import com.christophsens.cvenhancer.usecase.TranslateCvUseCase
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CvenhancerApplication{
    @Bean
    fun improveCvPort(openAiChatPort: OpenAiChatPort): ImproveCvPort {
        return ImproveCvUseCase(openAiChatPort)
    }

    @Bean
    fun translateCvPort(openAiChatPort: OpenAiChatPort): TranslateCvPort {
        return TranslateCvUseCase(openAiChatPort)
    }

    @Bean
    fun compareVacancyPort(openAiChatPort: OpenAiChatPort): CompareVacancyPort {
        return CompareVacancyWithOpenAIUseCase(openAiChatPort)
    }

    @Bean
    fun createCoverLetterPort(openAiChatPort: OpenAiChatPort): CreateCoverLetterPort {
        return CreateCoverLetterUseCase(openAiChatPort)
    }

}

fun main(args: Array<String>) {
    runApplication<CvenhancerApplication>(*args)
}