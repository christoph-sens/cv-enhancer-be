package com.christophsens.cvenhancer.controller;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import com.christophsens.cvenhancer.dto.MessageDto;
import com.christophsens.cvenhancer.service.CvService;
import com.christophsens.cvenhancer.utils.IsoUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping(value = "/translate/{languageCode}/{countryCode}", produces = "application/json")
    @Operation(summary = "Translates a document in the desired language for a given country")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved resource"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal technical error")
    })
    public Mono<MessageDto> translateCv(@RequestBody String cv, @PathVariable String languageCode, @PathVariable String countryCode) {
        if (!IsoUtils.isValidIsoLocale(languageCode, countryCode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid language or country code");
        }
        return Mono.just(new MessageDto(cvService.translateCv(cv, languageCode, countryCode)));
    }

    @PostMapping(value = "/improve", produces = "application/json")
    @Operation(summary = "Improves a CV in the desired language for a given country")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved resource"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal technical error")
    })
    public Mono<MessageDto> improveCv(@RequestBody String cv) {
        return Mono.just(new MessageDto(cvService.improveCv(cv)));
    }

    @PostMapping(value = "/compare-with-vacancy", produces = "application/json")
    @Operation(summary = "Compares the CV with the vacancy and gives feedback on how to improve")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved resource"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal technical error")
    })
    public Mono<MessageDto> compareCvToVacancy(@RequestBody CvVacancyDto cvVacancyDto) {
        return Mono.just(new MessageDto(cvService.compareCvAndVacancay(cvVacancyDto)));
    }

    @PostMapping(value = "/create-cover-letter", produces = "application/json")
    @Operation(summary = "Creates a cover letter for a given CV and vacancy")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved resource"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal technical error")
    })
    public Mono<MessageDto> createCoverLetter(@RequestBody CvVacancyDto cvVacancyDto) {
        return Mono.just(new MessageDto(cvService.createCoverLetter(cvVacancyDto)));
    }
}