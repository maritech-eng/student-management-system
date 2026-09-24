package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Event;
import com.example.studentmanagement.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(event));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable String id, @Valid @RequestBody Event event) {
        return ResponseEntity.ok(service.update(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
