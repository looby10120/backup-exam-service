package com.digitalacademy.examservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @Size(min = 1, max = 255, message = "Please type your Question name text size between 1 - 255")
    private String questionText;

    @Size(min = 1, max = 255, message = "Please type your Question name pic size between 1 - 255")
    private String questionPic;

    @Size(min = 1, max = 255, message = "Please type your Question name type size between 1 - 255")
    private String questionType;

    @NotNull
    private Long questionExamId;

    @Column
    @UpdateTimestamp
    private Date questionLastUpdate;

    @NotNull
    private Long questionUserCreate;

}
