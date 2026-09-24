package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
}
