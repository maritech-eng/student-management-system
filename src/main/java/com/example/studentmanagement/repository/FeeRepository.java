package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Fee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeeRepository extends MongoRepository<Fee, String> {
}
