package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Hostel;
import com.example.studentmanagement.service.HostelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel/hostels")
public class HostelController {

    @Autowired
    private HostelService service;

    @GetMapping
    public List<Hostel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hostel> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Hostel> create(@Valid @RequestBody Hostel hostel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(hostel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hostel> update(@PathVariable String id, @Valid @RequestBody Hostel hostel) {
        return ResponseEntity.ok(service.update(id, hostel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
