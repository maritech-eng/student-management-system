package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Attendance;
import com.example.studentmanagement.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @GetMapping
    public List<Attendance> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Attendance> create(@Valid @RequestBody Attendance attendance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(attendance));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> update(@PathVariable String id, @Valid @RequestBody Attendance attendance) {
        return ResponseEntity.ok(service.update(id, attendance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getByStudent(@PathVariable String studentId) {
        return service.getByStudent(studentId);
    }

    @GetMapping("/student/{studentId}/percentage")
    public Map<String, Object> getPercentage(@PathVariable String studentId,
                                              @RequestParam(defaultValue = "75") double minimum) {
        return service.getPercentageForStudent(studentId, minimum);
    }
}
