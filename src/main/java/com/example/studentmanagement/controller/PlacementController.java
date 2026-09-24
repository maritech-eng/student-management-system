package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Placement;
import com.example.studentmanagement.service.PlacementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
public class PlacementController {

    @Autowired
    private PlacementService service;

    @GetMapping
    public List<Placement> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Placement> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Placement> create(@Valid @RequestBody Placement placement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(placement));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Placement> update(@PathVariable String id, @Valid @RequestBody Placement placement) {
        return ResponseEntity.ok(service.update(id, placement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
