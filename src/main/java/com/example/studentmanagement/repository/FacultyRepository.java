package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Faculty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultyRepository extends MongoRepository<Faculty, String> {
}
