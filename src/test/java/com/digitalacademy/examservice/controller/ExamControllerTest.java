package com.digitalacademy.examservice.controller;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.controllers.ExamController;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.mock.ExamMockTest;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.services.ExamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExamControllerTest {
    private static final Logger log = LogManager.getLogger(ExamService.class.getName());
    private ExamMockTest examMockTest;

    @Mock
    ExamService examService;

    @InjectMocks
    ExamController examController;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        examController = new ExamController(examService);
        mvc = MockMvcBuilders.standaloneSetup(examController).build();
    }

    @DisplayName("Test get list all exam should return exam id and exam name")
    @Test
    void testGetAllExam() throws Exception {
        when(examService.getExam()).thenReturn(examMockTest.getListExamAllController());

        MvcResult mvcResult = mvc.perform(get("/exam/list_exam"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONArray data = new JSONArray(resp.getString("data"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("success", status.get("message"));

        assertEquals("1", data.getJSONObject(0).get("exam_id").toString());
        assertEquals("Test001", data.getJSONObject(0).get("exam_name").toString());

        assertEquals("2", data.getJSONObject(1).get("exam_id").toString());
        assertEquals("Test002", data.getJSONObject(1).get("exam_name").toString());


        verify(examService, times(1)).getExam();
    }

    @DisplayName("Test get exam by id 1 should return question and answer")
    @Test
    void testGetExamByIdSuccess() throws Exception {
        Long requestId = 1L;
        when(examService.getExamById(requestId)).thenReturn(examMockTest.getExamResponseMock());
        MvcResult mvcResult = mvc.perform(get("/exam/" + requestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONObject data = new JSONObject(resp.getString("data"));
        JSONArray questions = new JSONArray(data.getString("questions"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("success", status.get("message"));

        assertEquals("1", data.get("exam_id").toString());
        assertEquals("Exam Mock", data.get("exam_name"));

        verify(examService, times(1)).getExamById(requestId);
    }

    @DisplayName("Test get exam by id 100 should return question and answer")
    @Test
    void testGetExamByIdFail() throws Exception {
        Long requestId = 99L;
        when(examService.getExamById(requestId)).thenThrow(new ExamServiceException(StatusResponse.GET_NOT_FOUND_RESOURCE_IN_DATABASE, HttpStatus.NOT_FOUND));
        MvcResult mvcResult = mvc.perform(get("/exam/" + requestId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));

        assertEquals("1699", status.get("code").toString());
        assertEquals("not found resource in database", status.get("message"));

        verify(examService, times(1)).getExamById(requestId);
    }


   /* @DisplayName("Test get exam by id 1 should return question and answer")
    @Test
    void testGetExamByIdEmpty() throws Exception {
        Long requestId = 100L;
        when(examService.getExamById(requestId)).thenThrow(new ExamServiceException(StatusResponse.GET_NOT_FOUND_RESOURCE_IN_DATABASE, HttpStatus.NOT_FOUND));
        MvcResult mvcResult = mvc.perform(get("/exam/" + requestId))
                .andExpect(status().isNotFound())
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));

        assertEquals("1699", status.get("code").toString());
        assertEquals("not found resource in database", status.get("message"));

        verify(examService, times(1)).getExamById(requestId);
    }*/

    @DisplayName("Test get list top 5 history exam should return exam id and exam name")
    @Test
    void testGetTop5HistoryExam() throws Exception {
        when(examService.getHistoryExamMost()).thenReturn(examMockTest.getHistoryExamTop5Mock());

        MvcResult mvcResult = mvc.perform(get("/exam/exam_most"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
      //  log.info("resp : "+ resp);
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONObject data = new JSONObject(resp.getString("data"));
        JSONArray history_most_do = new JSONArray(data.getString("history_most_do"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("success", status.get("message"));
       // log.info("history_most_do[{}] : "+ history_most_do.getJSONObject(0).get("exam_id"));

        assertEquals("1", history_most_do.getJSONObject(0).get("exam_id").toString());
        assertEquals("Test001", history_most_do.getJSONObject(0).get("exam_name").toString());
        assertEquals("4", history_most_do.getJSONObject(0).get("count_all_do").toString());

        assertEquals("2", history_most_do.getJSONObject(1).get("exam_id").toString());
        assertEquals("Test002", history_most_do.getJSONObject(1).get("exam_name").toString());
        assertEquals("2", history_most_do.getJSONObject(1).get("count_all_do").toString());


        verify(examService, times(1)).getHistoryExamMost();
    }

    @DisplayName("Test get list top 5 history exam should return exam id and exam name")
    @Test
    void testGetUserLastDoExam() throws Exception {
        Long requestId = 1L;
        when(examService.getUserLastDoExam(requestId)).thenReturn(examMockTest.getUserLastDoExamMock());

        MvcResult mvcResult = mvc.perform(get("/exam//last_exam/"+ requestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONObject data = new JSONObject(resp.getString("data"));

        JSONArray data_array = new JSONArray(data.getString("last_do"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("success", status.get("message"));

        assertEquals("2", data.get("count_do_exam").toString());
        assertEquals("5", data.get("count_exam").toString());

        assertEquals("Test001", data_array.getJSONObject(0).get("exam_name").toString());
        assertEquals("Test002", data_array.getJSONObject(1).get("exam_name").toString());

        verify(examService, times(1)).getUserLastDoExam(requestId);
    }

    @DisplayName("Test get list top 5 history exam should return exam id and exam name")
    @Test
    void testGetHistoryUser() throws Exception {
        Long requestId = 1L;
        when(examService.getHistoryUser(requestId)).thenReturn(examMockTest.getHistoryUser());

        MvcResult mvcResult = mvc.perform(get("/exam/history/"+ requestId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));
        JSONObject data = new JSONObject(resp.getString("data"));

        JSONArray data_history = new JSONArray(data.getString("all_history"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("success", status.get("message"));

        assertEquals("1", data_history.getJSONObject(0).get("exam_id").toString());
        assertEquals("Exam mock", data_history.getJSONObject(0).get("exam_name"));
        assertEquals("10", data_history.getJSONObject(0).get("exam_total_score").toString());
        assertEquals("8", data_history.getJSONObject(0).get("point_exam").toString());

        verify(examService, times(1)).getHistoryUser(requestId);
    }




    @Test
    @DisplayName("test createHistory return data success")
    void createHistory() throws Exception{

        HistoryExam historyExamRequest = examMockTest.sethistoryCreateMock();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(historyExamRequest);

        when(examService.createHistoryExam(historyExamRequest)).thenReturn(examMockTest.gethistoryCreateMock());

        MvcResult mvcResult = mvc.perform(post("/exam/create_history")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();

        JSONObject resp = new JSONObject(mvcResult.getResponse().getContentAsString());
        JSONObject status = new JSONObject(resp.getString("status"));

        assertEquals("1000", status.get("code").toString());
        assertEquals("created success", status.get("message"));

    }
}
