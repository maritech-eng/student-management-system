package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Result;
import com.example.studentmanagement.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService service;

    @GetMapping
    public List<Result> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Result> create(@Valid @RequestBody Result result) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(result));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> update(@PathVariable String id, @Valid @RequestBody Result result) {
        return ResponseEntity.ok(service.update(id, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public List<Result> getByStudent(@PathVariable String studentId) {
        return service.getByStudent(studentId);
    }

    @GetMapping("/student/{studentId}/marksheet")
    public Map<String, Object> getMarksheet(@PathVariable String studentId) {
        return service.getMarksheet(studentId);
    }
}
