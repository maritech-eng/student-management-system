package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Notice;
import com.example.studentmanagement.service.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService service;

    @GetMapping
    public List<Notice> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notice> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Notice> create(@Valid @RequestBody Notice notice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(notice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notice> update(@PathVariable String id, @Valid @RequestBody Notice notice) {
        return ResponseEntity.ok(service.update(id, notice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
