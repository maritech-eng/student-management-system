package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Course> create(@Valid @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable String id, @Valid @RequestBody Course course) {
        return ResponseEntity.ok(service.update(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
