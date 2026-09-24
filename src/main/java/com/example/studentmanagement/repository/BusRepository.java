package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {
}
