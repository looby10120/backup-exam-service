package com.digitalacademy.examservice.repositories;

import com.digitalacademy.examservice.models.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findAllByChoiceQuestionId(Long id);
}
