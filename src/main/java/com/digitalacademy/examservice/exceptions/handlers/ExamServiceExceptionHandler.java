package com.digitalacademy.examservice.exceptions.handlers;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.controllers.ExamController;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.models.ResponseModel;
import com.digitalacademy.examservice.models.StatusModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExamServiceExceptionHandler {
    @ExceptionHandler(value = {ExamServiceException.class})
    public HttpEntity<ResponseModel> handleExamServiceException(ExamServiceException e) {
        return new ResponseModel(new StatusModel(
                e.getStatusResponse().getCode(),
                e.getStatusResponse().getMessage())
        ).build(e.getHttpStatus());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public HttpEntity<ResponseModel> handleHttpRequestMethodNotSupportedException() {
        StatusResponse statusResponse = StatusResponse.GET_REQUEST_WRONG_URL_PATH;

        return new ResponseModel(
                new StatusModel(statusResponse.getCode(), "request error")
        ).build(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, HttpMediaTypeNotSupportedException.class, MissingRequestHeaderException.class})
    public HttpEntity<ResponseModel> handleReturnBadRequest() {
        StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;

        return new ResponseModel(
                new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
        ).build(HttpStatus.BAD_REQUEST);
    }
}
