package com.digitalacademy.examservice.repositories;

import com.digitalacademy.examservice.models.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findAllByExamId(Long id);
}
