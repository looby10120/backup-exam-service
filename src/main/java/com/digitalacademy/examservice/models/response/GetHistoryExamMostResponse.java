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
public class GetHistoryExamMostResponse {


    @JsonProperty("history_most_do")
    private ArrayList<GetHistoryTopFire> getHistoryTopFireArrayList;

    public GetHistoryExamMostResponse() {
    }

    public GetHistoryExamMostResponse(ArrayList<GetHistoryTopFire> getHistoryTopFireArrayList) {
        this.getHistoryTopFireArrayList = getHistoryTopFireArrayList;
    }

}
