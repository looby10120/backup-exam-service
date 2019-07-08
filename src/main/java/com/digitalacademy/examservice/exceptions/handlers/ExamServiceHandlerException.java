package com.digitalacademy.examservice.exceptions.handlers;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.models.ResponseModel;
import com.digitalacademy.examservice.models.StatusModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExamServiceHandlerException {

//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public HttpEntity<ResponseModel> handlersBadRequestException() {
//        StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;
//
//        return new ResponseModel(
//                new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
//        ).build(HttpStatus.BAD_REQUEST);
//    }
//

    @ExceptionHandler(value = {ExamServiceException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpEntity<ResponseModel> handleExamServiceException(ExamServiceException e) {
        return new ResponseModel(new StatusModel(
                e.getStatusResponse().getCode(),
                e.getStatusResponse().getMessage())
        ).build(e.getHttpStatus());
    }


}
