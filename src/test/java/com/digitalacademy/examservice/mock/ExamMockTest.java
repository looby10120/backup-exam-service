package com.digitalacademy.examservice.mock;

import com.digitalacademy.examservice.models.Choice;
import com.digitalacademy.examservice.models.Exam;
import com.digitalacademy.examservice.models.HistoryExam;
import com.digitalacademy.examservice.models.Question;
import com.digitalacademy.examservice.models.response.*;

import java.util.*;

public class ExamMockTest {

    public static List<GetListExamResponse> getListExamAllController() {
        GetListExamResponse listExam1 = new GetListExamResponse();
        listExam1.setExamId(1L);
        listExam1.setExamName("Test001");
        listExam1.setExamTotalScore(10);

        GetListExamResponse listExam2 = new GetListExamResponse();
        listExam2.setExamId(2L);
        listExam2.setExamName("Test002");
        listExam2.setExamTotalScore(10);

        List<GetListExamResponse> examList = new ArrayList<>();
        examList.add(listExam1);
        examList.add(listExam2);
        return examList;
    }

    public static List<Exam> getListExamAllService() {
        List<Exam> examList = new ArrayList<>();
        Exam listExam1 = new Exam();
        listExam1.setExamId(1L);
        listExam1.setExamName("Test001");
        listExam1.setExamTotalScore(10);
        examList.add(listExam1);

        Exam listExam2 = new Exam();
        listExam2.setExamId(2L);
        listExam2.setExamName("Test002");
        listExam2.setExamTotalScore(10);
        examList.add(listExam2);

        return examList;
    }

    public static List<Exam> getListExamAllService5Element() {
        List<Exam> examList = new ArrayList<>();
        Exam listExam1 = new Exam();
        listExam1.setExamId(1L);
        listExam1.setExamName("Test001");
        listExam1.setExamTotalScore(10);
        examList.add(listExam1);

        Exam listExam2 = new Exam();
        listExam2.setExamId(2L);
        listExam2.setExamName("Test002");
        listExam2.setExamTotalScore(10);
        examList.add(listExam2);

        Exam listExam3 = new Exam();
        listExam3.setExamId(3L);
        listExam3.setExamName("Test003");
        listExam3.setExamTotalScore(10);
        examList.add(listExam3);

        Exam listExam4 = new Exam();
        listExam4.setExamId(4L);
        listExam4.setExamName("Test004");
        listExam4.setExamTotalScore(10);
        examList.add(listExam4);

        Exam listExam5 = new Exam();
        listExam5.setExamId(5L);
        listExam5.setExamName("Test005");
        listExam5.setExamTotalScore(10);
        examList.add(listExam5);

        return examList;
    }

    public static GetExamResponse getExamResponseMock() {
        ArrayList<QuestionsResponse> questionsResponseArray = new ArrayList<>();
        GetExamResponse getExamResponse = new GetExamResponse(questionsResponseArray);
        getExamResponse.setExamId(1L);
        getExamResponse.setExamName("Exam Mock");
        return getExamResponse;

    }

    public static List<Choice> getChoiceMock() {
        ArrayList<Choice> choiceList = new ArrayList<>();
        Choice choice = new Choice();
        choice.setChoiceId(1L);
        choice.setChoiceQuestionId(1L);
        choice.setChoiceQuestionExamId(1L);
        choice.setChoicePic(null);
        choice.setChoiceText("2");
        choice.setChoiceScore(1);
        choice.setChoiceLastUpdate(null);
        choice.setChoiceUserCreate(1L);
        choiceList.add(choice);

        Choice choices = new Choice();
        choices.setChoiceId(2L);
        choices.setChoiceQuestionId(1L);
        choices.setChoiceQuestionExamId(1L);
        choices.setChoicePic(null);
        choices.setChoiceText("5");
        choices.setChoiceScore(0);
        choices.setChoiceLastUpdate(null);
        choices.setChoiceUserCreate(1L);
        choiceList.add(choices);

        Choice choice2 = new Choice();
        choice2.setChoiceId(3L);
        choice2.setChoiceQuestionId(1L);
        choice2.setChoiceQuestionExamId(1L);
        choice2.setChoicePic(null);
        choice2.setChoiceText("15");
        choice2.setChoiceScore(0);
        choice2.setChoiceLastUpdate(null);
        choice2.setChoiceUserCreate(1L);
        choiceList.add(choice2);

        Choice choice3 = new Choice();
        choice3.setChoiceId(4L);
        choice3.setChoiceQuestionId(1L);
        choice3.setChoiceQuestionExamId(1L);
        choice3.setChoicePic(null);
        choice3.setChoiceText("54");
        choice3.setChoiceScore(0);
        choice3.setChoiceLastUpdate(null);
        choice3.setChoiceUserCreate(1L);
        choiceList.add(choice3);

        return choiceList;
    }

    public static List<Choice> getChoiceByOneMock() {
        ArrayList<Choice> choiceList = new ArrayList<>();
        Choice choice = new Choice();
        choice.setChoiceId(1L);
        choice.setChoiceQuestionId(1L);
        choice.setChoiceQuestionExamId(1L);
        choice.setChoicePic(null);
        choice.setChoiceText("2");
        choice.setChoiceScore(1);
        choice.setChoiceLastUpdate(null);
        choice.setChoiceUserCreate(1L);
        choiceList.add(choice);

        return choiceList;
    }

    public static List<Question> getQuestionMock() {
        ArrayList<Question> questionsList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1L);
        question.setQuestionExamId(1L);
        question.setQuestionPic(null);
        question.setQuestionText("1+1");
        question.setQuestionType("single");
        question.setQuestionLastUpdate(null);
        question.setQuestionUserCreate(1L);
        questionsList.add(question);

        Question questions = new Question();
        questions.setQuestionId(2L);
        questions.setQuestionExamId(1L);
        questions.setQuestionPic(null);
        questions.setQuestionText("2*5");
        questions.setQuestionType("single");
        question.setQuestionLastUpdate(null);
        questions.setQuestionUserCreate(1L);
        questionsList.add(questions);

        return questionsList;
    }

    public static List<Question> getQuestionByOneMock() {
        ArrayList<Question> questionsList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1L);
        question.setQuestionExamId(1L);
        question.setQuestionPic(null);
        question.setQuestionText("1+1");
        question.setQuestionType("single");
        question.setQuestionLastUpdate(null);
        question.setQuestionUserCreate(1L);
        questionsList.add(question);

        return questionsList;
    }


    public static Exam getAllExamMock() {
        Exam exam = new Exam();
        exam.setExamId(1L);
        exam.setExamName("Exam mock");
        return exam;
    }

    public static List<Question> getListQuestionMock() {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1L);
        question.setQuestionPic(null);
        question.setQuestionText("1+1");
        question.setQuestionType("single");

        Question question2 = new Question();
        question2.setQuestionId(2L);
        question2.setQuestionPic(null);
        question2.setQuestionText("2*5");
        question2.setQuestionType("single");

        questionList.add(question);
        questionList.add(question2);

        return questionList;
    }

    public static List<Choice> getListChoiceByQeustion1Mock() {
        List<Choice> choiceList = new ArrayList<>();
        Choice choice = new Choice();

        choice.setChoicePic(null);
        choice.setChoiceScore(0);
        choice.setChoiceText("1");
        choiceList.add(choice);

        Choice choice2 = new Choice();

        choice2.setChoicePic(null);
        choice2.setChoiceScore(1);
        choice2.setChoiceText("2");
        choiceList.add(choice2);

        Choice choice3 = new Choice();

        choice3.setChoicePic(null);
        choice3.setChoiceScore(0);
        choice3.setChoiceText("3");
        choiceList.add(choice3);

        Choice choice4 = new Choice();

        choice4.setChoicePic(null);
        choice4.setChoiceScore(0);
        choice4.setChoiceText("4");
        choiceList.add(choice4);

        return choiceList;
    }

    public static List<Choice> getListChoiceByQeustion2Mock() {
        List<Choice> choiceList = new ArrayList<>();
        Choice choice = new Choice();

        choice.setChoicePic(null);
        choice.setChoiceScore(0);
        choice.setChoiceText("7");
        choiceList.add(choice);

        Choice choice2 = new Choice();

        choice2.setChoicePic(null);
        choice2.setChoiceScore(0);
        choice2.setChoiceText("8");
        choiceList.add(choice2);

        Choice choice3 = new Choice();

        choice3.setChoicePic(null);
        choice3.setChoiceScore(0);
        choice3.setChoiceText("9");
        choiceList.add(choice3);

        Choice choice4 = new Choice();

        choice4.setChoicePic(null);
        choice4.setChoiceScore(1);
        choice4.setChoiceText("10");
        choiceList.add(choice4);

        return choiceList;
    }

    public static ArrayList<GetHistoryTopFire> getHistoryExamTop5ArrayMock() {

        ArrayList<GetHistoryTopFire> historyArrayList = new ArrayList<>();

        GetHistoryTopFire topFire = new GetHistoryTopFire();
        topFire.setExamId(1L);
        topFire.setExamName("Test001");
        topFire.setCountAllDo(4L);
        historyArrayList.add(topFire);

        GetHistoryTopFire topFires = new GetHistoryTopFire();
        topFires.setExamId(2L);
        topFires.setExamName("Test002");
        topFires.setCountAllDo(2L);
        historyArrayList.add(topFires);

        //GetHistoryExamMostResponse getHistoryExamMostResponse = new GetHistoryExamMostResponse(historyArrayList);
        return historyArrayList;
    }

    public static GetHistoryExamMostResponse getHistoryExamTop5Mock() {

        ArrayList<GetHistoryTopFire> historyArrayList = new ArrayList<>();

        GetHistoryTopFire topFire = new GetHistoryTopFire();
        topFire.setExamId(1L);
        topFire.setExamName("Test001");
        topFire.setCountAllDo(4L);
        historyArrayList.add(topFire);

        GetHistoryTopFire topFires = new GetHistoryTopFire();
        topFires.setExamId(2L);
        topFires.setExamName("Test002");
        topFires.setCountAllDo(2L);
        historyArrayList.add(topFires);

        GetHistoryExamMostResponse getHistoryExamMostResponse = new GetHistoryExamMostResponse(historyArrayList);
        return getHistoryExamMostResponse;
    }

    public static ArrayList<HistoryExam> getHistoryExamArrayListMock() {
        ArrayList<HistoryExam> historyExamArrayList = new ArrayList<>();

        Date date = new Date();

        HistoryExam historyExam = new HistoryExam();
        historyExam.setHistoryEmployeeId("1");
        historyExam.setHistoryExamId(1L);
        historyExam.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam);

        HistoryExam historyExam2 = new HistoryExam();
        historyExam2.setHistoryEmployeeId("1");
        historyExam2.setHistoryExamId(2L);
        historyExam2.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam2);

        return historyExamArrayList;
    }

    public static ArrayList<HistoryExam> getHistoryExamArrayListMockWith5Element() {
        ArrayList<HistoryExam> historyExamArrayList = new ArrayList<>();

        Date date = new Date();

        HistoryExam historyExam = new HistoryExam();
        historyExam.setHistoryEmployeeId("1");
        historyExam.setHistoryExamId(1L);
        historyExam.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam);

        HistoryExam historyExam2 = new HistoryExam();
        historyExam2.setHistoryEmployeeId("1");
        historyExam2.setHistoryExamId(2L);
        historyExam2.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam2);

        HistoryExam historyExam3 = new HistoryExam();
        historyExam3.setHistoryEmployeeId("1");
        historyExam3.setHistoryExamId(3L);
        historyExam3.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam3);

        HistoryExam historyExam4 = new HistoryExam();
        historyExam4.setHistoryEmployeeId("1");
        historyExam4.setHistoryExamId(4L);
        historyExam4.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam4);

        HistoryExam historyExam5 = new HistoryExam();
        historyExam5.setHistoryEmployeeId("1");
        historyExam5.setHistoryExamId(5L);
        historyExam5.setHistoryLastUpdate(date);
        historyExamArrayList.add(historyExam5);

        return historyExamArrayList;
    }

    public static GetUserLastDoExam getUserLastDoExamMock() {
        GetUserLastDoExam getUserLastDoExam = new GetUserLastDoExam();
        ArrayList<GetUserLastDoExamContent> getUserLastDoExamContentArrayList = new ArrayList<>();

        GetUserLastDoExamContent getUserLastDoExamContent = new GetUserLastDoExamContent();
        getUserLastDoExamContent.setExamId(1L);
        getUserLastDoExamContent.setExamName("Test001");
        getUserLastDoExamContent.setTimestamp(1111L);
        getUserLastDoExamContentArrayList.add(getUserLastDoExamContent);

        GetUserLastDoExamContent getUserLastDoExamContent2 = new GetUserLastDoExamContent();
        getUserLastDoExamContent2.setExamId(2L);
        getUserLastDoExamContent2.setExamName("Test002");
        getUserLastDoExamContent2.setTimestamp(1112L);
        getUserLastDoExamContentArrayList.add(getUserLastDoExamContent2);

        getUserLastDoExam.setCountDoExam(2L);
        getUserLastDoExam.setCountExam(5L);
        getUserLastDoExam.setGetUserLastDoExamContent(getUserLastDoExamContentArrayList);

        return getUserLastDoExam;
    }

    public static ArrayList<HistoryExam> historyExam() {
        Date date = new Date();
        ArrayList<HistoryExam> history = new ArrayList<>();

        HistoryExam historyList = new HistoryExam();
        historyList.setHistoryExamId(1L);
        historyList.setHistoryScore(8);
        historyList.setHistoryLastUpdate(date);
        history.add(historyList);
        return history;
    }

    public static GetHistoryUser getHistoryUser() {
        Date date = new Date();
        ArrayList<GetHistoryUserDoExam> history = new ArrayList<>();

        GetHistoryUserDoExam historyList = new GetHistoryUserDoExam();
        historyList.setExamId(1L);
        historyList.setExamName("Exam mock");
        historyList.setExamTotalScore(10);
        historyList.setPointExam(8);
        historyList.setTimestamp(date);
        history.add(historyList);

        GetHistoryUser getHistory = new GetHistoryUser();
        getHistory.setGetHistoryUserDoExam(history);
        return getHistory;
    }


    public static HistoryExam sethistoryCreateMock() {

        HistoryExam historyExam = new HistoryExam();
        historyExam.setHistoryId(1L);
        historyExam.setHistoryExamId(1L);
        historyExam.setHistoryEmployeeId("1");
        historyExam.setHistoryScore(10);
        historyExam.setHistoryTime(30);

        return historyExam;
    }

    public static HistoryExam gethistoryCreateMock() {

        HistoryExam historyExam = new HistoryExam();
        historyExam.setHistoryId(1L);
        historyExam.setHistoryExamId(1L);
        historyExam.setHistoryEmployeeId("1");
        historyExam.setHistoryScore(10);
        historyExam.setHistoryTime(30);

        return historyExam;
    }

    public static HistoryExam gethistoryCreateBodyFailMock() {

        HistoryExam historyExam = new HistoryExam();
        historyExam.setHistoryId(1L);
        historyExam.setHistoryExamId(1L);
        return historyExam;
    }

    public static ArrayList<HistoryExam> historyExamEmpty() {
        ArrayList<HistoryExam> history = new ArrayList<>();
        return history;
    }


}
