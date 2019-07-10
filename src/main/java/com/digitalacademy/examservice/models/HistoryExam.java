package com.digitalacademy.examservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@Entity
public class HistoryExam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long historyId;

    @NotNull
    private Long historyExamId;

    @NotNull
    private String historyEmployeeId;

    @NotNull
    @Min(value = 1, message = "please score your History exam")
    private Integer historyScore;

    private Integer historyTime;

    @Column
    @UpdateTimestamp
    private Date historyLastUpdate;

}




