package com.christophsens.cvenhancer.controller;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import com.christophsens.cvenhancer.dto.MessageDto;
import com.christophsens.cvenhancer.service.CvService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@WebFluxTest(CvController.class)
public class CvControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CvService cvService;

    @Test
    public void testTranslateCv_Ok() {
        String expectedResult = "Translated CV";
        given(cvService.translateCv(anyString(), anyString(), anyString())).willReturn(expectedResult);

        webClient
            .post()
            .uri("/api/cv/translate/en/US")
            .bodyValue("Sample CV")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(MessageDto.class)
            .isEqualTo(new MessageDto(expectedResult));

    }
    
    @Test
    public void testTranslateCv_BadParams() {
        String expectedResult = "Translated CV";
        given(cvService.translateCv(anyString(), anyString(), anyString())).willReturn(expectedResult);

        webClient
            .post()
            .uri("/api/cv/translate/xx/XX")
            .bodyValue("Sample CV")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isBadRequest();
    }

    @Test
    public void testImproveCv() {
        String expectedResult = "Improved CV";
        given(cvService.improveCv(anyString())).willReturn(expectedResult);

        webClient
            .post()
            .uri("/api/cv/improve")
            .bodyValue("Sample CV")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(MessageDto.class)
            .isEqualTo(new MessageDto(expectedResult));
    }

    @Test
    public void testCompareCvToVacancy() {
        String expectedResult = "Comparison Result";
        given(cvService.compareCvAndVacancay(any(CvVacancyDto.class))).willReturn(expectedResult);
        CvVacancyDto cvVacancyDto = new CvVacancyDto("cv", "vacancy");
        webClient
            .post()
            .uri("/api/cv/compare-with-vacancy")
            .bodyValue(cvVacancyDto)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(MessageDto.class)
            .isEqualTo(new MessageDto(expectedResult));
    }

    @Test
    public void testCreateCoverLetter() {
        String expectedResult = "Cover Letter";
        given(cvService.createCoverLetter(any(CvVacancyDto.class))).willReturn(expectedResult);
        CvVacancyDto cvVacancyDto = new CvVacancyDto("cv", "vacancy");
        webClient
            .post()
            .uri("/api/cv/create-cover-letter")
            .bodyValue(cvVacancyDto)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(MessageDto.class)
            .isEqualTo(new MessageDto(expectedResult));
    }
}