package com.christophsens.cvenhancer.service;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import com.christophsens.cvenhancer.prompt.CompareVacancyPrompt;
import com.christophsens.cvenhancer.prompt.CreateCoverLetterPrompt;
import com.christophsens.cvenhancer.prompt.ImprovePrompt;
import com.christophsens.cvenhancer.prompt.TranslationPrompt;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CvService {

    private final ChatModel chatModel;

    @Autowired
    public CvService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String translateCv(String cv, String languageCode, String countryCode) {
        ChatResponse response = chatModel.call(TranslationPrompt.getPrompt(cv, languageCode, countryCode));
        return extractContent(response);
    }

    public String improveCv(String cv) {
        ChatResponse response = chatModel.call(ImprovePrompt.getPrompt(cv));
        return extractContent(response);
    }

    public String compareCvAndVacancay(CvVacancyDto dto) {
        ChatResponse response = chatModel.call(CompareVacancyPrompt.getPrompt(dto));
        return extractContent(response);
    }

    public String createCoverLetter(CvVacancyDto dto) {
        ChatResponse response = chatModel.call(CreateCoverLetterPrompt.getPrompt(dto));
        return extractContent(response);
    }

    private String extractContent(ChatResponse response) {
        return response.getResult().getOutput().getText();

    }
}