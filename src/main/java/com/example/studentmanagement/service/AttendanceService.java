package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Attendance;
import com.example.studentmanagement.repository.AttendanceRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    public List<Attendance> getAll() {
        return repository.findAll();
    }

    public Attendance getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found with id: " + id));
    }

    public Attendance create(Attendance attendance) {
        return repository.save(attendance);
    }

    public Attendance update(String id, Attendance attendance) {
        Attendance existing = getById(id);
        attendance.setId(existing.getId());
        return repository.save(attendance);
    }

    public void delete(String id) {
        Attendance existing = getById(id);
        repository.deleteById(existing.getId());
    }

    public List<Attendance> getByStudent(String studentId) {
        return repository.findAll().stream()
                .filter(a -> studentId.equals(a.getStudentId()))
                .toList();
    }

    /**
     * Attendance percentage = Present Days / Total Working Days x 100.
     * A configurable minimum threshold (default 75%) drives the
     * "belowMinimum" warning flag consumed by the frontend.
     */
    public Map<String, Object> getPercentageForStudent(String studentId, double minimumRequired) {
        List<Attendance> records = getByStudent(studentId);
        long totalDays = records.size();
        long presentDays = records.stream().filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus())).count();
        double percentage = totalDays == 0 ? 0 : (presentDays * 100.0) / totalDays;

        Map<String, Object> result = new HashMap<>();
        result.put("studentId", studentId);
        result.put("totalWorkingDays", totalDays);
        result.put("presentDays", presentDays);
        result.put("absentDays", totalDays - presentDays);
        result.put("percentage", Math.round(percentage * 100.0) / 100.0);
        result.put("minimumRequired", minimumRequired);
        result.put("belowMinimum", percentage < minimumRequired);
        return result;
    }
}
