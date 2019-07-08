package com.digitalacademy.examservice.exceptions;

import com.digitalacademy.examservice.constants.StatusResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExamServiceException extends Exception {

    private HttpStatus httpStatus;
    private StatusResponse statusResponse;

    public ExamServiceException(StatusResponse statusResponse, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.statusResponse = statusResponse;
    }
}
