package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Placement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlacementRepository extends MongoRepository<Placement, String> {
}
