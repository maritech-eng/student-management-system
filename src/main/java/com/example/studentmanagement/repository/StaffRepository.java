package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StaffRepository extends MongoRepository<Staff, String> {
}
