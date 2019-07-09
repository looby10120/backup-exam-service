package com.digitalacademy.examservice.controllers;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.models.response.*;
import com.digitalacademy.examservice.models.ResponseModel;
import com.digitalacademy.examservice.models.StatusModel;
import com.digitalacademy.examservice.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {

    private ExamService examService;


    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{id}")
    public HttpEntity<ResponseModel> getExamById(@PathVariable String id){
        if (id.trim().length() != id.length()) {
            StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;
            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.BAD_REQUEST);
        }
        try {
            Long longId = Long.valueOf(id);
            GetExamResponse getExamResponse = examService.getExamById(longId);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );

            return ResponseEntity.ok(new ResponseModel(status, getExamResponse));
        } catch (NumberFormatException e) {
            StatusResponse statusResponse = StatusResponse.GET_REQUEST_WRONG_URL_PATH;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.NOT_FOUND);
        } catch (ExamServiceException e) {
            StatusResponse statusResponse = e.getStatusResponse();

            return ResponseEntity.status(e.getHttpStatus()).body(new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list_exam")
    public HttpEntity<ResponseModel> getAllExam() {
        try {
            List<GetListExamResponse> listExam = examService.getExam();
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, listExam));
        } catch (ExamServiceException e) {
            StatusResponse statusResponse = e.getStatusResponse();

            return ResponseEntity.status(e.getHttpStatus()).body(new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exam_most")
    public HttpEntity<ResponseModel> getAllHistoryExam() {
        try {
            GetHistoryExamMostResponse historyExam = examService.getHistoryExamMost();
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, historyExam));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/last_exam/{id}")
    public HttpEntity<ResponseModel> getUserLastDoExam(@PathVariable String id) {
        if (id.trim().length() != id.length()) {
            StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;
            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.BAD_REQUEST);
        }
        try {
            Long longId = Long.valueOf(id);
            GetUserLastDoExam getUserLastDoExam = examService.getUserLastDoExam(longId);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, getUserLastDoExam));
        } catch (NumberFormatException e) {
            StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.BAD_REQUEST);
        } catch (ExamServiceException e) {
            StatusResponse statusResponse = e.getStatusResponse();

            return ResponseEntity.status(e.getHttpStatus()).body(new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/history/{id}")
    public HttpEntity<ResponseModel> getHistoryUserDoExam(@PathVariable String id) {
        if (id.trim().length() != id.length()) {
            StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;
            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.BAD_REQUEST);
        }
        try {
            Long longId = Long.valueOf(id);
            GetHistoryUser historyExam = examService.getHistoryUser(longId);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, historyExam));
        } catch (NumberFormatException e) {
            StatusResponse statusResponse = StatusResponse.GET_BAD_REQUEST;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.BAD_REQUEST);
        } catch (ExamServiceException e) {
            StatusResponse statusResponse = e.getStatusResponse();

            return ResponseEntity.status(e.getHttpStatus()).body(new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create_history")
    public HttpEntity<ResponseModel> createHistoryExam(@Valid @RequestBody HistoryExam body, Errors error) throws ExamServiceException {

        if(error.hasErrors()){
            throw new ExamServiceException(
                    StatusResponse.GET_BAD_REQUEST,
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            HistoryExam historyExam = examService.createHistoryExam(body);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_CREATED_SUCCESS.getCode(), StatusResponse.GET_CREATED_SUCCESS.getMessage()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(status));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
