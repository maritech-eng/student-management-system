package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
}
