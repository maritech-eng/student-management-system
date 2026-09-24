package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Department;
import com.example.studentmanagement.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public List<Department> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Department> create(@Valid @RequestBody Department department) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> update(@PathVariable String id, @Valid @RequestBody Department department) {
        return ResponseEntity.ok(service.update(id, department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
