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
        return response.getResult().getOutput().getContent();
    }

    public String improveCv(String cv) {
        ChatResponse response = chatModel.call(ImprovePrompt.getPrompt(cv));
        return response.getResult().getOutput().getContent();
    }

    public String compareCvAndVacancay(CvVacancyDto dto) {
        ChatResponse response = chatModel.call(CompareVacancyPrompt.getPrompt(dto));
        return response.getResult().getOutput().getContent();
    }

    public String createCoverLetter(CvVacancyDto dto) {
        ChatResponse response = chatModel.call(CreateCoverLetterPrompt.getPrompt(dto));
        return response.getResult().getOutput().getContent();
    }
}