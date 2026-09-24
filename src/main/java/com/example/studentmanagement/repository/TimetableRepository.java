package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Timetable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimetableRepository extends MongoRepository<Timetable, String> {
}
