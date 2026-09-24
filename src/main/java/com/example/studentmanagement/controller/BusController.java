package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Bus;
import com.example.studentmanagement.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transport/buses")
public class BusController {

    @Autowired
    private BusService service;

    @GetMapping
    public List<Bus> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Bus> create(@Valid @RequestBody Bus bus) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(bus));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> update(@PathVariable String id, @Valid @RequestBody Bus bus) {
        return ResponseEntity.ok(service.update(id, bus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
