package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.HostelAllocation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HostelAllocationRepository extends MongoRepository<HostelAllocation, String> {
}
