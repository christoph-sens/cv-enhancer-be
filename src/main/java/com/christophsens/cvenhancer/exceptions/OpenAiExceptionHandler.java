package com.christophsens.cvenhancer.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class OpenAiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    ProblemDetail handleResponseStatusException(ResponseStatusException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), ex.getReason());
        problemDetail.setTitle(ex.getStatusCode().toString());
        return problemDetail;
    }
}
