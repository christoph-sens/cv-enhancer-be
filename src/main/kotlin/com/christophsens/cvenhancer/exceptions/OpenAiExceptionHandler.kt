package com.christophsens.cvenhancer.exceptions

import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class OpenAiExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(ex: ResponseStatusException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(ex.statusCode, ex.reason)
        problemDetail.title = ex.statusCode.toString()
        return problemDetail
    }
}