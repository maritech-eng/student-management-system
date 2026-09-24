package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.LibraryTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryTransactionRepository extends MongoRepository<LibraryTransaction, String> {
}
