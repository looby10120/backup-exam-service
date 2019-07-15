package com.digitalacademy.examservice.controllers;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.models.response.*;
import com.digitalacademy.examservice.models.ResponseModel;
import com.digitalacademy.examservice.models.StatusModel;
import com.digitalacademy.examservice.services.ExamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    public static final Logger log = LogManager.getLogger(ExamController.class.getName());

    private ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{id}")

    public HttpEntity<ResponseModel> getExamById(@PathVariable String id) {
        if (id.trim().length() != id.length()) {
            StatusResponse statusResponse = StatusResponse.GET_REQUEST_WRONG_URL_PATH;
            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.NOT_FOUND);
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
            GetHistoryExamMostResponse getHistoryExamMostResponse = examService.getHistoryExamMost();
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, getHistoryExamMostResponse));
        } catch (Exception e) {
            StatusResponse statusResponse = StatusResponse.GET_DEATH_SERVER;

            return new ResponseModel(
                    new StatusModel(statusResponse.getCode(), statusResponse.getMessage())
            ).build(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/last_exam")
    public HttpEntity<ResponseModel> getUserLastDoExam(@RequestHeader("id") Long userId) {
        try {

            GetUserLastDoExam getUserLastDoExam = examService.getUserLastDoExam(userId);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, getUserLastDoExam));
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

    @GetMapping("/history")

    public HttpEntity<ResponseModel> getHistoryUserDoExam(@RequestHeader("id") Long userId) {
        try {

            GetHistoryUser gethistoryUser = examService.getHistoryUser(userId);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );
            return ResponseEntity.ok(new ResponseModel(status, gethistoryUser));
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
    public HttpEntity<ResponseModel> createHistoryExam(@Valid @RequestHeader("id") Long userId, @RequestBody HistoryExam body, Errors error) throws ExamServiceException {

        if (error.hasErrors() || body.getHistoryExamId() == null || body.getHistoryScore() == null) {
            throw new ExamServiceException(
                    StatusResponse.GET_BAD_REQUEST,
                    HttpStatus.BAD_REQUEST
            );
        }
        try {
            body.setHistoryUserId(userId);
            examService.createHistoryExam(body);
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
