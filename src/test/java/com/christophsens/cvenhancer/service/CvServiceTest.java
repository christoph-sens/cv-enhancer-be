package com.christophsens.cvenhancer.service;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CvServiceTest {

    @Mock
    private ChatModel chatModel;

    @InjectMocks
    private CvService cvService;

    private ChatResponse createMockChatResponse(String content) {
        Generation generation = new Generation(new AssistantMessage(content));
        return new ChatResponse(List.of(generation));
    }

    @Test
    void testTranslateCv() {
        String cv = "Sample CV";
        String languageCode = "en";
        String countryCode = "US";
        String expectedResponse = "Translated CV";
        
        when(chatModel.call(any(Prompt.class))).thenReturn(createMockChatResponse(expectedResponse));

        String result = cvService.translateCv(cv, languageCode, countryCode);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testImproveCv() {
        String cv = "Sample CV";
        String expectedResponse = "Improved CV";

        when(chatModel.call(any(Prompt.class))).thenReturn(createMockChatResponse(expectedResponse));
        
        String result = cvService.improveCv(cv);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testCompareCvAndVacancay() {
        CvVacancyDto dto = new CvVacancyDto("cv", "vacancy");
        String expectedResponse = "Comparison Result";

        when(chatModel.call(any(Prompt.class))).thenReturn(createMockChatResponse(expectedResponse));

        String result = cvService.compareCvAndVacancay(dto);

        assertEquals(expectedResponse, result);
    }

    @Test
    void testCreateCoverLetter() {
        CvVacancyDto dto = new CvVacancyDto("cv", "vacancy");
        String expectedResponse = "Cover Letter";

        when(chatModel.call(any(Prompt.class))).thenReturn(createMockChatResponse(expectedResponse));

        String result = cvService.createCoverLetter(dto);

        assertEquals(expectedResponse, result);
    }
}
