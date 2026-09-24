package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Room;
import com.example.studentmanagement.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @GetMapping
    public List<Room> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Room> create(@Valid @RequestBody Room room) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(room));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable String id, @Valid @RequestBody Room room) {
        return ResponseEntity.ok(service.update(id, room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
