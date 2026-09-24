package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.TransportAllocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportAllocationRepository extends MongoRepository<TransportAllocation, String> {
}
