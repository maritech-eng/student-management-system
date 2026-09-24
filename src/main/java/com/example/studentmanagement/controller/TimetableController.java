package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Timetable;
import com.example.studentmanagement.service.TimetableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    @Autowired
    private TimetableService service;

    @GetMapping
    public List<Timetable> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timetable> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Timetable> create(@Valid @RequestBody Timetable timetable) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(timetable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timetable> update(@PathVariable String id, @Valid @RequestBody Timetable timetable) {
        return ResponseEntity.ok(service.update(id, timetable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
