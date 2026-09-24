package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.TransportAllocation;
import com.example.studentmanagement.service.TransportAllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transport/allocations")
public class TransportAllocationController {

    @Autowired
    private TransportAllocationService service;

    @GetMapping
    public List<TransportAllocation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportAllocation> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransportAllocation> create(@Valid @RequestBody TransportAllocation transportAllocation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transportAllocation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportAllocation> update(@PathVariable String id, @Valid @RequestBody TransportAllocation transportAllocation) {
        return ResponseEntity.ok(service.update(id, transportAllocation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
