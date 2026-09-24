package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Exam;
import com.example.studentmanagement.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService service;

    @GetMapping
    public List<Exam> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Exam> create(@Valid @RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(exam));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> update(@PathVariable String id, @Valid @RequestBody Exam exam) {
        return ResponseEntity.ok(service.update(id, exam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
