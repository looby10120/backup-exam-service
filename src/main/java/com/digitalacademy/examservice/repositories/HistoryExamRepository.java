package com.digitalacademy.examservice.repositories;

import com.digitalacademy.examservice.models.HistoryExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface HistoryExamRepository extends JpaRepository<HistoryExam, Long> {
    Long countByHistoryExamId(Long id);
    ArrayList<HistoryExam> findAllByHistoryEmployeeId(String id);
}
