package com.digitalacademy.examservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetHistoryUserDoExam {

    private Long examId;
    private String examName;
    private int examTotalScore;         // score exam
    private int pointExam;              // score user
    private Date timestamp;

//    public GetListExamResponse() {
//    }
}
