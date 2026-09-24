package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
