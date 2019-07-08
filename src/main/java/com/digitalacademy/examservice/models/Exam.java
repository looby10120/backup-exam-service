package com.digitalacademy.examservice.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;

    @NotNull
    @Size(min = 1, max = 255, message = "Please type your exam name size between 1 - 255")
    private String examName;

    @NotNull
    private int examTotalScore;

    @Column
    @UpdateTimestamp
    private Date examLastUpdate;

    @NotNull
    private Long examUserCreate;

}
