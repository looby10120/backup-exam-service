package com.digitalacademy.examservice.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetHistoryTopFire implements Comparable<GetHistoryTopFire>{

    private Long examId;
    private String examName;
    private Long countAllDo;
    private Long countQuestion;
    private int examTotalScore;

    public GetHistoryTopFire() {
    }

    public int compareTo(GetHistoryTopFire compareGetHistoryTopFire) {

        Long compareCountAllDo = ((GetHistoryTopFire) compareGetHistoryTopFire).getCountAllDo();

        return (int) (this.countAllDo - compareCountAllDo);
    }
}
