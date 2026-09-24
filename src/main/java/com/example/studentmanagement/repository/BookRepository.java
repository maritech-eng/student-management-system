package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
