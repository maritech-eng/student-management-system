package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<Result, String> {
}
