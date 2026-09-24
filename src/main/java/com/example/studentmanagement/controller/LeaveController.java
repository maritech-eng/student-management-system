package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Leave;
import com.example.studentmanagement.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService service;

    @GetMapping
    public List<Leave> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leave> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Leave> create(@Valid @RequestBody Leave leave) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(leave));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leave> update(@PathVariable String id, @Valid @RequestBody Leave leave) {
        return ResponseEntity.ok(service.update(id, leave));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Leave> approve(@PathVariable String id, @RequestParam String approvedBy) {
        return ResponseEntity.ok(service.setStatus(id, "APPROVED", approvedBy));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Leave> reject(@PathVariable String id, @RequestParam String approvedBy) {
        return ResponseEntity.ok(service.setStatus(id, "REJECTED", approvedBy));
    }
}
