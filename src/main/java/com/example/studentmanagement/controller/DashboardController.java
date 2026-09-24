package com.example.studentmanagement.controller;

import com.example.studentmanagement.model.Attendance;
import com.example.studentmanagement.model.Fee;
import com.example.studentmanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired private StudentRepository studentRepository;
    @Autowired private StaffRepository staffRepository;
    @Autowired private FacultyRepository facultyRepository;
    @Autowired private DepartmentRepository departmentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private AttendanceRepository attendanceRepository;
    @Autowired private FeeRepository feeRepository;
    @Autowired private ResultRepository resultRepository;

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        Map<String, Object> data = new HashMap<>();

        data.put("totalStudents", studentRepository.count());
        data.put("totalStaff", staffRepository.count());
        data.put("totalFaculty", facultyRepository.count());
        data.put("totalDepartments", departmentRepository.count());
        data.put("totalCourses", courseRepository.count());

        // Attendance summary
        List<Attendance> attendanceList = attendanceRepository.findAll();
        long present = attendanceList.stream().filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus())).count();
        long total = attendanceList.size();
        double attendancePercentage = total == 0 ? 0 : (present * 100.0) / total;
        Map<String, Object> attendanceSummary = new HashMap<>();
        attendanceSummary.put("totalRecords", total);
        attendanceSummary.put("present", present);
        attendanceSummary.put("absent", total - present);
        attendanceSummary.put("overallPercentage", Math.round(attendancePercentage * 100.0) / 100.0);
        data.put("attendanceSummary", attendanceSummary);

        // Fees summary
        List<Fee> fees = feeRepository.findAll();
        double totalCollected = fees.stream().mapToDouble(f -> f.getPaidAmount() == null ? 0 : f.getPaidAmount()).sum();
        double totalPending = fees.stream().mapToDouble(f -> f.getPendingAmount() == null ? 0 : f.getPendingAmount()).sum();
        Map<String, Object> feesSummary = new HashMap<>();
        feesSummary.put("totalCollected", totalCollected);
        feesSummary.put("totalPending", totalPending);
        feesSummary.put("recordCount", fees.size());
        data.put("feesSummary", feesSummary);

        // Results summary
        Map<String, Object> resultsSummary = new HashMap<>();
        resultsSummary.put("recordCount", resultRepository.count());
        data.put("resultsSummary", resultsSummary);

        return data;
    }
}
