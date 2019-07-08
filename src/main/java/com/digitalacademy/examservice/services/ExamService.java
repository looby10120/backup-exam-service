package com.digitalacademy.examservice.services;

import com.digitalacademy.examservice.constants.StatusResponse;
import com.digitalacademy.examservice.exceptions.ExamServiceException;
import com.digitalacademy.examservice.models.Choice;
import com.digitalacademy.examservice.models.Exam;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.models.Question;
import com.digitalacademy.examservice.models.response.*;
import com.digitalacademy.examservice.repositories.ChoiceRepository;
import com.digitalacademy.examservice.repositories.ExamRepository;
import com.digitalacademy.examservice.repositories.HistoryExamRepository;
import com.digitalacademy.examservice.repositories.QuestionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {

    private static final Logger log = LogManager.getLogger(ExamService.class.getName());

    private ExamRepository examRepository;
    private QuestionRepository questionRepository;
    private ChoiceRepository choiceRepository;
    private HistoryExamRepository historyExamRepository;

    public ExamService(ExamRepository examRepository, QuestionRepository questionRepository, ChoiceRepository choiceRepository, HistoryExamRepository historyExamRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
        this.historyExamRepository = historyExamRepository;
    }

    public List<GetListExamResponse> getExam() {
        List<Exam> examList = examRepository.findAll();
        List<GetListExamResponse> examResponseList = new ArrayList<>();
        for (int i = 0; i < examList.size(); i++) {
            GetListExamResponse getListExamResponse = new GetListExamResponse();
            getListExamResponse.setExamId(examList.get(i).getExamId());
            getListExamResponse.setExamName(examList.get(i).getExamName());
            getListExamResponse.setExamTotalScore(examList.get(i).getExamTotalScore());
            getListExamResponse.setCountQuestion(questionRepository.countByQuestionExamId(examList.get(i).getExamId()));

            examResponseList.add(getListExamResponse);
        }

        return examResponseList;
    }

    public GetExamResponse getExamById(Long id) throws Exception {

        Exam exam = examRepository.findAllByExamId(id);
        // if find exam by id but not found ,it will return null and throw 404 not found
        if (exam == null){
            throw new ExamServiceException(
                    StatusResponse.GET_NOT_FOUND_RESOURCE_IN_DATABASE,
                    HttpStatus.NOT_FOUND
            );
        }
        //end check exam is null

        List<Question> questionList = questionRepository.findAllByQuestionExamId(id);
        ArrayList<QuestionsResponse> getQuestionResponseArrayList = new ArrayList<>();

        for (int i = 0; i < questionList.size(); i++) {

            // get loop choice
            List<Choice> choiceList = choiceRepository.findAllByChoiceQuestionId(questionList.get(i).getQuestionId());
            ArrayList<GetChoiceResponse> choiceResponseArrayList = new ArrayList<>();

            for (int j = 0; j < choiceList.size(); j++) {
                GetChoiceResponse getChoiceResponse = new GetChoiceResponse();
                getChoiceResponse.setChoicePic(choiceList.get(j).getChoicePic());
                getChoiceResponse.setChoiceScore(choiceList.get(j).getChoiceScore());
                getChoiceResponse.setChoiceText(choiceList.get(j).getChoiceText());
                choiceResponseArrayList.add(getChoiceResponse);
            }
            // end loop choice

            // create head question
            GetQuestionHeadResponse getQuestionHeadResponse = new GetQuestionHeadResponse();
            getQuestionHeadResponse.setQuestionPic(questionList.get(i).getQuestionPic());
            getQuestionHeadResponse.setQuestionText(questionList.get(i).getQuestionText());
            getQuestionHeadResponse.setQuestionType(questionList.get(i).getQuestionType());
            // end head question

            // create 1 question with head question and multiple choice
            QuestionsResponse questionsResponse = new QuestionsResponse(getQuestionHeadResponse, choiceResponseArrayList);
            // end 1 question

            getQuestionResponseArrayList.add(questionsResponse);
        }
        GetExamResponse getExamResponse = new GetExamResponse(getQuestionResponseArrayList);
        getExamResponse.setExamId(exam.getExamId());
        getExamResponse.setExamName(exam.getExamName());

        return getExamResponse;
    }

    public GetHistoryExamMostResponse getHistoryExamMost() {
        List<Exam> examList = examRepository.findAll();
        ArrayList<GetHistoryTopFire> historyArrayList = new ArrayList<>();
        int countLoop;
        if (examList.size() < 5) {
            countLoop = examList.size();
        } else {
            countLoop = 5;
        }
        for (int i = 0; i < countLoop; i++) {
            GetHistoryTopFire getHistoryTopFire = new GetHistoryTopFire();
            getHistoryTopFire.setExamId(examList.get(i).getExamId());
            getHistoryTopFire.setExamName(examList.get(i).getExamName());
            getHistoryTopFire.setCountAllDo(historyExamRepository.countByHistoryExamId(examList.get(i).getExamId()));
            getHistoryTopFire.setCountQuestion(questionRepository.countByQuestionExamId(examList.get(i).getExamId()));
            getHistoryTopFire.setExamTotalScore(examList.get(i).getExamTotalScore());
            historyArrayList.add(getHistoryTopFire);
        }
        Collections.reverse(historyArrayList);
        GetHistoryExamMostResponse getHistoryExamMostResponse = new GetHistoryExamMostResponse(historyArrayList);

        return getHistoryExamMostResponse;
    }


    public List<Question> getQuestion() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionById(Long question_exam_id) {
        return questionRepository.findAllByQuestionExamId(question_exam_id);
    }

    public List<Choice> getChoice() {
        return choiceRepository.findAll();
    }

    public List<Choice> getChoiceById(Long choice_que_id) {
        return choiceRepository.findAllByChoiceQuestionId(choice_que_id);
    }

    public GetUserLastDoExam getUserLastDoExam(Long id) {
        GetUserLastDoExam getUserLastDoExam = new GetUserLastDoExam();

        ArrayList<HistoryExam> historyExamArrayList = historyExamRepository.findAllByHistoryUserId(id);

        getUserLastDoExam.setCountDoExam((long) historyExamArrayList.size());
        getUserLastDoExam.setCountExam((long) examRepository.findAll().size());

        ArrayList<GetUserLastDoExamContent> getUserLastDoExamContentArrayList = new ArrayList<>();

        int countLoop;
        if (historyExamArrayList.size() < 5) {
            countLoop = historyExamArrayList.size();
        } else {
            countLoop = 5;
        }

        for (int j = 0; j < countLoop; j++) {
            GetUserLastDoExamContent getUserLastDoExamContent = new GetUserLastDoExamContent();
            getUserLastDoExamContent.setExamId(historyExamArrayList.get(j).getHistoryExamId());
            getUserLastDoExamContent.setExamName(examRepository.findAllByExamId(historyExamArrayList.get(j).getHistoryExamId()).getExamName());

            getUserLastDoExamContent.setTimestamp(historyExamArrayList.get(j).getHistoryLastUpdate().getTime());
            getUserLastDoExamContentArrayList.add(getUserLastDoExamContent);
        }

        getUserLastDoExam.setGetUserLastDoExamContent(getUserLastDoExamContentArrayList);

        return getUserLastDoExam;
    }

    public GetHistoryUser getHistoryUser(Long id) {
        List<HistoryExam> historyList = historyExamRepository.findAllByHistoryUserId(id);
        ArrayList<GetHistoryUserDoExam> historyUserDoExam = new ArrayList<>();

        for (int i = 0; i < historyList.size(); i++) {
            GetHistoryUserDoExam historyAll = new GetHistoryUserDoExam();
            historyAll.setExamId(historyList.get(i).getHistoryExamId());
            historyAll.setExamName(examRepository.findAllByExamId(historyList.get(i).getHistoryExamId()).getExamName());
            historyAll.setExamTotalScore(examRepository.findAllByExamId(historyList.get(i).getHistoryExamId()).getExamTotalScore());
            historyAll.setPointExam(historyList.get(i).getHistoryScore());
            historyAll.setTimestamp(historyList.get(i).getHistoryLastUpdate());
            historyUserDoExam.add(historyAll);
        }

        GetHistoryUser getHistoryUser = new GetHistoryUser();
        getHistoryUser.setGetHistoryUserDoExam(historyUserDoExam);

        return getHistoryUser;
    }

    public List<HistoryExam> getHistoryExam(){
        return historyExamRepository.findAll();
    }

    public HistoryExam createHistoryExam(HistoryExam boby){
        return historyExamRepository.save(boby);
    }

}
