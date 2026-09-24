package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Fee;
import com.example.studentmanagement.service.FeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    @Autowired
    private FeeService service;

    @GetMapping
    public List<Fee> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fee> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Fee> create(@Valid @RequestBody Fee fee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(fee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fee> update(@PathVariable String id, @Valid @RequestBody Fee fee) {
        return ResponseEntity.ok(service.update(id, fee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public List<Fee> getByStudent(@PathVariable String studentId) {
        return service.getByStudent(studentId);
    }
}
