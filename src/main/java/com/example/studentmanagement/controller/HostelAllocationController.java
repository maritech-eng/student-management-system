package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.HostelAllocation;
import com.example.studentmanagement.service.HostelAllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel/allocations")
public class HostelAllocationController {

    @Autowired
    private HostelAllocationService service;

    @GetMapping
    public List<HostelAllocation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HostelAllocation> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<HostelAllocation> create(@Valid @RequestBody HostelAllocation hostelAllocation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(hostelAllocation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HostelAllocation> update(@PathVariable String id, @Valid @RequestBody HostelAllocation hostelAllocation) {
        return ResponseEntity.ok(service.update(id, hostelAllocation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
