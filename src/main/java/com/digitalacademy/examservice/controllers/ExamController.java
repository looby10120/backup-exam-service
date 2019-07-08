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
    public HttpEntity<ResponseModel> getExamById(@PathVariable Long id) {
        try {
            GetExamResponse getExamResponse = examService.getExamById(id);
            StatusModel status = new StatusModel(
                    StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
            );

            return ResponseEntity.ok(new ResponseModel(status, getExamResponse));
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
        List<GetListExamResponse> listExam = examService.getExam();
        StatusModel status = new StatusModel(
                StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
        );
        return ResponseEntity.ok(new ResponseModel(status, listExam));
    }

    @GetMapping("/exam_most")
    public HttpEntity<ResponseModel> getAllHistoryExam() {
        GetHistoryExamMostResponse historyExam = examService.getHistoryExamMost();
        StatusModel status = new StatusModel(
                StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
        );
        return ResponseEntity.ok(new ResponseModel(status, historyExam));
    }

    @GetMapping("/last_exam/{id}")
    public HttpEntity<ResponseModel> getUserLastDoExam(@PathVariable Long id) {
        GetUserLastDoExam getUserLastDoExam = examService.getUserLastDoExam(id);
        StatusModel status = new StatusModel(
                StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
        );
        return ResponseEntity.ok(new ResponseModel(status, getUserLastDoExam));
    }

    @GetMapping("/history/{id}")
    public HttpEntity<ResponseModel> getHistoryUserDoExam(@PathVariable Long id) {

        GetHistoryUser historyExam = examService.getHistoryUser(id);
        StatusModel status = new StatusModel(
                StatusResponse.GET_RESPONSE_SUCCESS.getCode(), StatusResponse.GET_RESPONSE_SUCCESS.getMessage()
        );
        return ResponseEntity.ok(new ResponseModel(status, historyExam));
    }

    /*@RequestMapping(path = "/list_history")
    public List<HistoryExam> getAllHistory() {
        List<HistoryExam> resp = examService.getHistoryExam();
        return resp;
    }*/

    @PostMapping("/create_history")
    public ResponseEntity<?> createHistoryExam(@Valid @RequestBody HistoryExam body) {

        HistoryExam historyExam = examService.createHistoryExam(body);
        StatusModel status = new StatusModel(
                StatusResponse.GET_CREATED_SUCCESS.getCode(), StatusResponse.GET_CREATED_SUCCESS.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseModel(status));
    }


}
