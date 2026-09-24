package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
}
