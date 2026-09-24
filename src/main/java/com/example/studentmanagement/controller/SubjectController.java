package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService service;

    @GetMapping
    public List<Subject> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Subject> create(@Valid @RequestBody Subject subject) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(subject));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable String id, @Valid @RequestBody Subject subject) {
        return ResponseEntity.ok(service.update(id, subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
