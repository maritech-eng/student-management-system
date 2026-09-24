package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.LibraryTransaction;
import com.example.studentmanagement.service.LibraryTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/transactions")
public class LibraryTransactionController {

    @Autowired
    private LibraryTransactionService service;

    @GetMapping
    public List<LibraryTransaction> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryTransaction> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryTransaction> create(@Valid @RequestBody LibraryTransaction libraryTransaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(libraryTransaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryTransaction> update(@PathVariable String id, @Valid @RequestBody LibraryTransaction libraryTransaction) {
        return ResponseEntity.ok(service.update(id, libraryTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/issue")
    public ResponseEntity<LibraryTransaction> issue(@RequestParam String bookId, @RequestParam String studentId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.issueBook(bookId, studentId));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LibraryTransaction> returnBook(@PathVariable String id) {
        return ResponseEntity.ok(service.returnBook(id));
    }
}
