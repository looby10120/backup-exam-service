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
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long choiceId;

    @Size(min = 1, max = 255, message = "Please type your exam name size between 1 - 255")
    private String choiceText;

    @Size(min = 1, max = 255, message = "Please type your exam name size between 1 - 255")
    private String choicePic;

    @Min(value = 1, message = "please score your exam")
    private Integer choiceScore;

    @NotNull
    private Long choiceQuestionId;

    @NotNull
    private Long choiceQuestionExamId;

    @Column
    @UpdateTimestamp
    private Date choiceLastUpdate;

    @NotNull
    private Long choiceUserCreate;

}
