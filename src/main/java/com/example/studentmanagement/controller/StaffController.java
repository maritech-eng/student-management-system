package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Staff;
import com.example.studentmanagement.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService service;

    @GetMapping
    public List<Staff> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Staff> create(@Valid @RequestBody Staff staff) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(staff));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> update(@PathVariable String id, @Valid @RequestBody Staff staff) {
        return ResponseEntity.ok(service.update(id, staff));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
