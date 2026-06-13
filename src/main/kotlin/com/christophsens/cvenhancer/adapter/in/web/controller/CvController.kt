package com.christophsens.cvenhancer.adapter.`in`.web.controller

import com.christophsens.cvenhancer.application.port.`in`.CompareVacancyPort
import com.christophsens.cvenhancer.application.port.`in`.CreateCoverLetterPort
import com.christophsens.cvenhancer.application.port.`in`.ImproveCvPort
import com.christophsens.cvenhancer.application.port.`in`.TranslateCvPort
import com.christophsens.cvenhancer.model.valueobject.LocaleValueObject
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/cv")
class CvController(
    private val improveCvPort: ImproveCvPort,
    private val translateCvPort: TranslateCvPort,
    private val compareVacancyPort: CompareVacancyPort,
    private val createCoverLetterPort: CreateCoverLetterPort,
) {

    @PostMapping(value = ["/translate/{languageCode}/{countryCode}"], produces = ["application/json"])
    @Operation(summary = "Translates a document in the desired language")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "CV translated successfully"),
            ApiResponse(responseCode = "400", description = "Invalid language or country code")
        ]
    )
    fun translateCv(
        @RequestBody cv: String,
        @PathVariable languageCode: String,
        @PathVariable countryCode: String
    ): Mono<MessageDto> {
        if (!IsoUtils.isValidIsoLocale(languageCode, countryCode)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid language or country code")
        }

        return Mono.just(MessageDto(translateCvPort.translate(cv, LocaleValueObject(languageCode, countryCode))))
    }

    @PostMapping(value = ["/improve"], produces = ["application/json"])
    @Operation(summary = "Improves a CV")
    @ApiResponse(responseCode = "200", description = "CV improved successfully")
    fun improveCv(@RequestBody cv: String): Mono<MessageDto> {
        return Mono.just(MessageDto(improveCvPort.improve(cv)))
    }

    @PostMapping(value = ["/compare-with-vacancy"], produces = ["application/json"])
    @Operation(summary = "Compares a CV with a vacancy")
    @ApiResponse(responseCode = "200", description = "CV compared with vacancy successfully")
    fun compareCvToVacancy(@RequestBody dto: CvVacancyDto): Mono<MessageDto> {
        return Mono.just(MessageDto(compareVacancyPort.compare(dto.cv, dto.vacancy)))
    }

    @PostMapping(value = ["/create-cover-letter"], produces = ["application/json"])
    @Operation(summary = "Creates a cover letter for a vacancy")
    @ApiResponse(responseCode = "200", description = "Cover letter created successfully")
    fun createCoverLetter(@RequestBody dto: CvVacancyDto): Mono<MessageDto> {
        return Mono.just(MessageDto(createCoverLetterPort.create(
           dto.cv, dto.vacancy
        )))
    }
}