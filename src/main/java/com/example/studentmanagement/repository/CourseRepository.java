package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {
}
