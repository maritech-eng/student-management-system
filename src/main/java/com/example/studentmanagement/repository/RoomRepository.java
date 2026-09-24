package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
