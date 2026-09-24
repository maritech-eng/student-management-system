package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DepartmentRepository extends MongoRepository<Department, String> {
}
