package com.digitalacademy.examservice.services;

import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.mock.ExamMockTest;
import com.digitalacademy.examservice.models.Choice;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.models.Question;
import com.digitalacademy.examservice.models.response.*;

import com.digitalacademy.examservice.repositories.ChoiceRepository;
import com.digitalacademy.examservice.repositories.ExamRepository;
import com.digitalacademy.examservice.repositories.HistoryExamRepository;
import com.digitalacademy.examservice.repositories.QuestionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExamServiceTest {

    private static final Logger log = LogManager.getLogger(ExamService.class.getName());
    private ExamMockTest examMockTest;
    @InjectMocks
    ExamService examService;

    @Mock
    ExamRepository examRepository;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    ChoiceRepository choiceRepository;

    @Mock
    HistoryExamRepository historyExamRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        examService = new ExamService(examRepository, questionRepository, choiceRepository, historyExamRepository);
    }

    @DisplayName("Test get list all exam should return exam id and exam name")
    @Test
    void testGetAllExam() throws Exception {
        when(examRepository.findAll()).thenReturn(examMockTest.getListExamAllService());
        when(questionRepository.countByQuestionExamId(1L)).thenReturn(10L);
        when(questionRepository.countByQuestionExamId(2L)).thenReturn(10L);

        List<GetListExamResponse> resp = examService.getExam();
        assertEquals("1", resp.get(0).getExamId().toString());
        assertEquals("Test001", resp.get(0).getExamName());
        assertEquals(10, resp.get(0).getExamTotalScore());
        assertEquals("10", resp.get(0).getCountQuestion().toString());

        assertEquals("2", resp.get(1).getExamId().toString());
        assertEquals("Test002", resp.get(1).getExamName());
        assertEquals(10, resp.get(0).getExamTotalScore());
        assertEquals("10", resp.get(0).getCountQuestion().toString());

    }

  /*  @DisplayName("Test get question should return list")
    @Test
    void testGetAllQuestion2() {
        when(questionRepository.findAll()).thenReturn(examMockTest.getQuestionMock());
        List<Question> resp = examService.getQuestion();
        log.info("Get All Question: ");

        assertEquals("1", resp.get(0).getQuestionId().toString());
        assertEquals("1", resp.get(0).getQuestionExamId().toString());
        assertNull(resp.get(0).getQuestionPic());
        assertEquals("1+1", resp.get(0).getQuestionText());
        assertEquals("single", resp.get(0).getQuestionType());
        assertNull(resp.get(0).getQuestionLastUpdate());
        assertEquals("1", resp.get(0).getQuestionUserCreate().toString());

        assertEquals("2", resp.get(1).getQuestionId().toString());
        assertEquals("1", resp.get(1).getQuestionExamId().toString());
        assertNull(resp.get(1).getQuestionPic());
        assertEquals("2*5", resp.get(1).getQuestionText());
        assertEquals("single", resp.get(1).getQuestionType());
        assertNull(resp.get(1).getQuestionLastUpdate());
        assertEquals("1", resp.get(1).getQuestionUserCreate().toString());
    }*/

/*    @DisplayName("Test get question By Id should return list")
    @Test
    void testGetQuestionById2() {
        Long requestParam = 1L;
        when(questionRepository.findAllByQuestionExamId(requestParam)).thenReturn(examMockTest.getQuestionByOneMock());
        List<Question> resp = examService.getQuestionById(requestParam);
        log.info("Get Question By Id:");

        assertEquals("1", resp.get(0).getQuestionId().toString());
        assertEquals("1", resp.get(0).getQuestionExamId().toString());
        assertNull(resp.get(0).getQuestionPic());
        assertEquals("1+1", resp.get(0).getQuestionText());
        assertEquals("single", resp.get(0).getQuestionType());
        assertNull(resp.get(0).getQuestionLastUpdate());
        assertEquals("1", resp.get(0).getQuestionUserCreate().toString());
    }*/

/*    @DisplayName("Test get choice should return list")
    @Test
    void testGetAllQuestion() {
        when(choiceRepository.findAll()).thenReturn(examMockTest.getChoiceMock());
        List<Choice> resp = examService.getChoice();
        log.info("Get All Question: ");

        assertEquals("1", resp.get(0).getChoiceId().toString());
        assertEquals("1", resp.get(0).getChoiceQuestionId().toString());
        assertEquals("1", resp.get(0).getChoiceQuestionExamId().toString());
        assertNull(resp.get(0).getChoicePic());
        assertEquals("2", resp.get(0).getChoiceText());
        assertEquals("1", resp.get(0).getChoiceScore().toString());
        assertNull(resp.get(0).getChoiceLastUpdate());
        assertEquals("1", resp.get(0).getChoiceUserCreate().toString());

        assertEquals("2", resp.get(1).getChoiceId().toString());
        assertEquals("1", resp.get(1).getChoiceQuestionId().toString());
        assertEquals("1", resp.get(1).getChoiceQuestionExamId().toString());
        assertNull(resp.get(1).getChoicePic());
        assertEquals("5", resp.get(1).getChoiceText());
        assertEquals("0", resp.get(1).getChoiceScore().toString());
        assertNull(resp.get(1).getChoiceLastUpdate());
        assertEquals("1", resp.get(1).getChoiceUserCreate().toString());

        assertEquals("3", resp.get(2).getChoiceId().toString());
        assertEquals("1", resp.get(2).getChoiceQuestionId().toString());
        assertEquals("1", resp.get(2).getChoiceQuestionExamId().toString());
        assertNull(resp.get(2).getChoicePic());
        assertEquals("15", resp.get(2).getChoiceText());
        assertEquals("0", resp.get(2).getChoiceScore().toString());
        assertNull(resp.get(2).getChoiceLastUpdate());
        assertEquals("1", resp.get(2).getChoiceUserCreate().toString());

        assertEquals("4", resp.get(3).getChoiceId().toString());
        assertEquals("1", resp.get(3).getChoiceQuestionId().toString());
        assertEquals("1", resp.get(3).getChoiceQuestionExamId().toString());
        assertNull(resp.get(3).getChoicePic());
        assertEquals("54", resp.get(3).getChoiceText());
        assertEquals("0", resp.get(3).getChoiceScore().toString());
        assertNull(resp.get(3).getChoiceLastUpdate());
        assertEquals("1", resp.get(3).getChoiceUserCreate().toString());
    }*/

  /*  @DisplayName("Test get question By Id should return list")
    @Test
    void testGetQuestionById() {
        Long choice_que_id = 1L;
        when(choiceRepository.findAllByChoiceQuestionId(choice_que_id)).thenReturn(examMockTest.getChoiceByOneMock());
        List<Choice> resp = examService.getChoiceById(choice_que_id);
        log.info("Get Question By Id:");

        assertEquals("1", resp.get(0).getChoiceId().toString());
        assertEquals("1", resp.get(0).getChoiceQuestionId().toString());
        assertEquals("1", resp.get(0).getChoiceQuestionExamId().toString());
        assertNull(resp.get(0).getChoicePic());
        assertEquals("2", resp.get(0).getChoiceText());
        assertEquals("1", resp.get(0).getChoiceScore().toString());
        assertNull(resp.get(0).getChoiceLastUpdate());
        assertEquals("1", resp.get(0).getChoiceUserCreate().toString());

    }*/

    @DisplayName("Test get question By Id should return list")
    @Test
    void testgetExamById() throws Exception {
        Long requestParam = 1L;
        Long requestParam2 = 2L;
        when(examRepository.findAllByExamId(requestParam)).thenReturn(examMockTest.getAllExamMock());
        when(questionRepository.findAllByQuestionExamId(requestParam)).thenReturn(examMockTest.getListQuestionMock());
        when(choiceRepository.findAllByChoiceQuestionId(requestParam)).thenReturn(examMockTest.getListChoiceByQeustion1Mock());
        when(choiceRepository.findAllByChoiceQuestionId(requestParam2)).thenReturn(examMockTest.getListChoiceByQeustion2Mock());

        GetExamResponse resp = this.examService.getExamById(requestParam);

        assertEquals("1", resp.getExamId().toString());
        assertEquals("Exam mock", resp.getExamName());
        assertEquals("1+1", resp.getQuestionsResponseArray().get(0).getGetQuestionHeadResponse().getQuestionText());

        assertEquals("2*5", resp.getQuestionsResponseArray().get(1).getGetQuestionHeadResponse().getQuestionText());
        assertEquals("7", resp.getQuestionsResponseArray().get(1).getGetChoiceResponseArray().get(0).getChoiceText());
        assertEquals("8", resp.getQuestionsResponseArray().get(1).getGetChoiceResponseArray().get(1).getChoiceText());
        assertEquals("9", resp.getQuestionsResponseArray().get(1).getGetChoiceResponseArray().get(2).getChoiceText());
        assertEquals("10", resp.getQuestionsResponseArray().get(1).getGetChoiceResponseArray().get(3).getChoiceText());
        assertEquals("1", resp.getQuestionsResponseArray().get(1).getGetChoiceResponseArray().get(3).getChoiceScore().toString());

    }

    @DisplayName("Test get Exam 5 Most should return list")
    @Test
    void testgetHistoryExamMost() throws Exception {
        Long requestParam = 1L;
        Long requestParam2 = 2L;
        when(examRepository.findAll()).thenReturn(examMockTest.getListExamAllService());

        when(historyExamRepository.countByHistoryExamId(requestParam)).thenReturn(4L);
        when(historyExamRepository.countByHistoryExamId(requestParam2)).thenReturn(2L);
        when(questionRepository.countByQuestionExamId(requestParam)).thenReturn(2L);
        when(questionRepository.countByQuestionExamId(requestParam2)).thenReturn(10L);

        GetHistoryExamMostResponse resp = this.examService.getHistoryExamMost();
        assertEquals("1", resp.getGetHistoryTopFireArrayList().get(1).getExamId().toString());
        assertEquals("Test001", resp.getGetHistoryTopFireArrayList().get(1).getExamName());
        assertEquals("4", resp.getGetHistoryTopFireArrayList().get(1).getCountAllDo().toString());
        assertEquals("2", resp.getGetHistoryTopFireArrayList().get(1).getCountQuestion().toString());
        assertEquals(10, resp.getGetHistoryTopFireArrayList().get(1).getExamTotalScore());

        assertEquals("2", resp.getGetHistoryTopFireArrayList().get(0).getExamId().toString());
        assertEquals("Test002", resp.getGetHistoryTopFireArrayList().get(0).getExamName());
        assertEquals("2", resp.getGetHistoryTopFireArrayList().get(0).getCountAllDo().toString());
        assertEquals("10", resp.getGetHistoryTopFireArrayList().get(0).getCountQuestion().toString());
        assertEquals(10, resp.getGetHistoryTopFireArrayList().get(0).getExamTotalScore());

    }

    @DisplayName("Test get Exam 5 Most should return list")
    @Test
    void testgetHistoryExamMostWith5Element() throws Exception {
        Long requestParam = 1L;
        Long requestParam2 = 2L;
        Long requestParam3 = 3L;
        Long requestParam4 = 4L;
        Long requestParam5 = 5L;
        when(examRepository.findAll()).thenReturn(examMockTest.getListExamAllService5Element());

        when(historyExamRepository.countByHistoryExamId(requestParam)).thenReturn(4L);
        when(historyExamRepository.countByHistoryExamId(requestParam2)).thenReturn(2L);
        when(historyExamRepository.countByHistoryExamId(requestParam3)).thenReturn(3L);
        when(historyExamRepository.countByHistoryExamId(requestParam4)).thenReturn(6L);
        when(historyExamRepository.countByHistoryExamId(requestParam5)).thenReturn(1L);

        GetHistoryExamMostResponse resp = this.examService.getHistoryExamMost();
        assertEquals("1", resp.getGetHistoryTopFireArrayList().get(4).getExamId().toString());
        assertEquals("Test001", resp.getGetHistoryTopFireArrayList().get(4).getExamName());
        assertEquals("4", resp.getGetHistoryTopFireArrayList().get(4).getCountAllDo().toString());

        assertEquals("2", resp.getGetHistoryTopFireArrayList().get(3).getExamId().toString());
        assertEquals("Test002", resp.getGetHistoryTopFireArrayList().get(3).getExamName());
        assertEquals("2", resp.getGetHistoryTopFireArrayList().get(3).getCountAllDo().toString());

        assertEquals("3", resp.getGetHistoryTopFireArrayList().get(2).getExamId().toString());
        assertEquals("Test003", resp.getGetHistoryTopFireArrayList().get(2).getExamName());
        assertEquals("3", resp.getGetHistoryTopFireArrayList().get(2).getCountAllDo().toString());

        assertEquals("4", resp.getGetHistoryTopFireArrayList().get(1).getExamId().toString());
        assertEquals("Test004", resp.getGetHistoryTopFireArrayList().get(1).getExamName());
        assertEquals("6", resp.getGetHistoryTopFireArrayList().get(1).getCountAllDo().toString());

        assertEquals("5", resp.getGetHistoryTopFireArrayList().get(0).getExamId().toString());
        assertEquals("Test005", resp.getGetHistoryTopFireArrayList().get(0).getExamName());
        assertEquals("1", resp.getGetHistoryTopFireArrayList().get(0).getCountAllDo().toString());

    }

    @DisplayName("Test get user last do exam must be return GetUserLastDoExam json")
    @Test
    void testGetUserLastDoExam() throws Exception {
        Long requestParam = 1L;
        when(examRepository.findAll()).thenReturn(examMockTest.getListExamAllService5Element());

        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(examMockTest.getHistoryExamArrayListMock());

        when(examRepository.findAllByExamId(1L)).thenReturn(examMockTest.getListExamAllService5Element().get(0));
        when(examRepository.findAllByExamId(2L)).thenReturn(examMockTest.getListExamAllService5Element().get(1));

        GetUserLastDoExam resp = this.examService.getUserLastDoExam(requestParam);

        assertEquals("2", resp.getCountDoExam().toString());
        assertEquals("5", resp.getCountExam().toString());
        assertEquals("Test001", resp.getGetUserLastDoExamContent().get(0).getExamName());
        assertEquals("Test002", resp.getGetUserLastDoExamContent().get(1).getExamName());

    }

    @DisplayName("Test get user last do exam must be with 5 element return GetUserLastDoExam json")
    @Test
    void testGetUserLastDoExamWith5Element() throws Exception {
        Long requestParam = 1L;
        when(examRepository.findAll()).thenReturn(examMockTest.getListExamAllService5Element());

        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(examMockTest.getHistoryExamArrayListMockWith5Element());

        when(examRepository.findAllByExamId(1L)).thenReturn(examMockTest.getListExamAllService5Element().get(0));
        when(examRepository.findAllByExamId(2L)).thenReturn(examMockTest.getListExamAllService5Element().get(1));
        when(examRepository.findAllByExamId(3L)).thenReturn(examMockTest.getListExamAllService5Element().get(2));
        when(examRepository.findAllByExamId(4L)).thenReturn(examMockTest.getListExamAllService5Element().get(3));
        when(examRepository.findAllByExamId(5L)).thenReturn(examMockTest.getListExamAllService5Element().get(4));

        GetUserLastDoExam resp = this.examService.getUserLastDoExam(requestParam);

        assertEquals("5", resp.getCountDoExam().toString());
        assertEquals("5", resp.getCountExam().toString());
        assertEquals("Test001", resp.getGetUserLastDoExamContent().get(0).getExamName());
        assertEquals("Test002", resp.getGetUserLastDoExamContent().get(1).getExamName());
        assertEquals("Test003", resp.getGetUserLastDoExamContent().get(2).getExamName());
        assertEquals("Test004", resp.getGetUserLastDoExamContent().get(3).getExamName());
        assertEquals("Test005", resp.getGetUserLastDoExamContent().get(4).getExamName());

    }

    @DisplayName("Test get all exam user do return list all exam history")
    @Test
    void testgetHistoryUserDoExam() throws Exception {
        Long requestParam = 1L;
        Long examId = 1L;
        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(examMockTest.historyExam());
        when(examRepository.findAllByExamId(examId)).thenReturn(examMockTest.getAllExamMock());

        GetHistoryUser resp = this.examService.getHistoryUser(requestParam);
        assertEquals("1", resp.getGetHistoryUserDoExam().get(0).getExamId().toString());
        assertEquals("Exam mock", resp.getGetHistoryUserDoExam().get(0).getExamName());
        assertEquals(8, resp.getGetHistoryUserDoExam().get(0).getPointExam());
    }


    @DisplayName("Test create history should return list")
    @Test
    void testCreateHistoryExam() {

        when(historyExamRepository.save(examMockTest.sethistoryCreateMock())).thenReturn(examMockTest.gethistoryCreateMock());
        HistoryExam resp = examService.createHistoryExam(examMockTest.gethistoryCreateMock());
        log.info("Get History By Id:");

        assertEquals("1", resp.getHistoryId().toString());
        assertEquals("1", resp.getHistoryExamId().toString());
        assertEquals("1", resp.getHistoryUserId().toString());
        assertEquals("10", resp.getHistoryScore().toString());
        assertEquals("30", resp.getHistoryTime().toString());
    }


    @DisplayName("Test last do exam with array empty then throws 1699")
    @Test
    void testGetUserLastDoExamWithArrayEmpty() throws Exception {
        Long requestParam = 2L;
        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(examMockTest.historyExamEmpty());
        ExamServiceException thrown = assertThrows(ExamServiceException.class,
                () -> examService.getUserLastDoExam(requestParam),
                "Expected getUserLastDoExam(requestParam) to throw, but it did't");
        assertEquals(1699,thrown.getStatusResponse().getCode());
        assertEquals("not found resource in database", thrown.getStatusResponse().getMessage());
    }

    @DisplayName("Test get exam by id with empty then throws 1699")
    @Test
    void testGetExamByIdWithArrayEmpty() throws Exception {
        Long requestParam = 2L;
        when(examRepository.findAllByExamId(requestParam)).thenReturn(null);
        ExamServiceException thrown = assertThrows(ExamServiceException.class,
                () -> examService.getExamById(requestParam),
                "Expected getExamById(requestParam) to throw, but it did't");
        assertEquals(1699,thrown.getStatusResponse().getCode());
        assertEquals("not found resource in database", thrown.getStatusResponse().getMessage());
    }

    @DisplayName("")
    @Test
    void testGetHistoryUserByIdWithArrayEmpty() throws Exception {
       Long requestParam = 1L;
        Long examId = 1L;
        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(null);
        ExamServiceException thrown = assertThrows(ExamServiceException.class,
                () -> examService.getExamById(examId));
        assertEquals(1699,thrown.getStatusResponse().getCode());
        assertEquals("not found resource in database", thrown.getStatusResponse().getMessage());
    }

    @DisplayName("")
    @Test
    void testGetHistoryUserIsEmpty() throws Exception {
        Long requestParam = 100L;
        when(historyExamRepository.findAllByHistoryUserId(requestParam)).thenReturn(examMockTest.historyExamEmpty());
        ExamServiceException examServiceException = assertThrows(ExamServiceException.class,
                () -> examService.getHistoryUser(requestParam));

        assertEquals(1699, examServiceException.getStatusResponse().getCode());
        assertEquals("not found resource in database", examServiceException.getStatusResponse().getMessage());
    }

}