package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Hostel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HostelRepository extends MongoRepository<Hostel, String> {
}
