package com.digitalacademy.examservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.util.ArrayList;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetExamResponse {

    private Long examId;
    private String examName;

    public GetExamResponse() {
    }

    @JsonProperty("questions")
    private ArrayList<QuestionsResponse> questionsResponseArray;

    public GetExamResponse(ArrayList<QuestionsResponse> questionsResponseArray) {
        this.questionsResponseArray = questionsResponseArray;
    }

}

