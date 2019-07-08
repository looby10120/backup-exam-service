package com.digitalacademy.examservice.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class QuestionsResponse implements Serializable {

    @JsonProperty("question")
    private GetQuestionHeadResponse getQuestionHeadResponse;

    @JsonProperty("choices")
    private ArrayList<GetChoiceResponse> getChoiceResponseArray;

    public QuestionsResponse(GetQuestionHeadResponse getQuestionHeadResponse, ArrayList<GetChoiceResponse> getChoiceResponseArray) {
        this.getQuestionHeadResponse = getQuestionHeadResponse;
        this.getChoiceResponseArray = getChoiceResponseArray;
    }

}
