package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepository extends MongoRepository<Notice, String> {
}
