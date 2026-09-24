package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Leave;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LeaveRepository extends MongoRepository<Leave, String> {
}
