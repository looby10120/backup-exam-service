package com.digitalacademy.examservice.repositories;

import com.digitalacademy.examservice.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByQuestionExamId(Long id);
    Long countByQuestionExamId(Long id);
}
