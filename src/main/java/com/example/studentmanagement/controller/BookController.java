package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Book;
import com.example.studentmanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable String id, @Valid @RequestBody Book book) {
        return ResponseEntity.ok(service.update(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
