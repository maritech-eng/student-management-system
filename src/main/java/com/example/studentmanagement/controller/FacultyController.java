package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Faculty;
import com.example.studentmanagement.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService service;

    @GetMapping
    public List<Faculty> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Faculty> create(@Valid @RequestBody Faculty faculty) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(faculty));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> update(@PathVariable String id, @Valid @RequestBody Faculty faculty) {
        return ResponseEntity.ok(service.update(id, faculty));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
