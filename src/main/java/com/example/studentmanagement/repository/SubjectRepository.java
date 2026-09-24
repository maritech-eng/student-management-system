package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}
