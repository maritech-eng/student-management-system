package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Result;
import com.example.studentmanagement.repository.ResultRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private ResultRepository repository;

    public List<Result> getAll() {
        return repository.findAll();
    }

    public Result getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));
    }

    public Result create(Result result) {
        applyGrading(result);
        return repository.save(result);
    }

    public Result update(String id, Result result) {
        Result existing = getById(id);
        result.setId(existing.getId());
        applyGrading(result);
        return repository.save(result);
    }

    public List<Result> getByStudent(String studentId) {
        return repository.findAll().stream()
                .filter(r -> studentId.equals(r.getStudentId()))
                .toList();
    }

    /**
     * Simple 10-point grading scale based on percentage, common in Indian
     * college marksheets. Grade point drives the marksheet's GPA/CGPA line.
     */
    private void applyGrading(Result result) {
        if (result.getMarks() == null || result.getMaxMarks() == null || result.getMaxMarks() == 0) {
            return;
        }
        double percentage = (result.getMarks() / result.getMaxMarks()) * 100;

        String grade;
        double gradePoint;
        if (percentage >= 90) { grade = "O";  gradePoint = 10; }
        else if (percentage >= 80) { grade = "A+"; gradePoint = 9; }
        else if (percentage >= 70) { grade = "A";  gradePoint = 8; }
        else if (percentage >= 60) { grade = "B+"; gradePoint = 7; }
        else if (percentage >= 50) { grade = "B";  gradePoint = 6; }
        else if (percentage >= 40) { grade = "C";  gradePoint = 5; }
        else { grade = "F"; gradePoint = 0; }

        result.setGrade(grade);
        result.setGradePoint(gradePoint);
    }

    /**
     * Builds a marksheet-style summary for one student: all subject results,
     * total marks, percentage and CGPA (credit-weighted average of grade
     * points, falling back to a simple average when no credits are set).
     */
    public Map<String, Object> getMarksheet(String studentId) {
        List<Result> results = getByStudent(studentId);

        double totalMarks = 0, totalMax = 0;
        double weightedPoints = 0, totalCredits = 0;

        for (Result r : results) {
            totalMarks += r.getMarks() == null ? 0 : r.getMarks();
            totalMax += r.getMaxMarks() == null ? 0 : r.getMaxMarks();
            int credits = r.getCredits() == null ? 1 : r.getCredits();
            double gp = r.getGradePoint() == null ? 0 : r.getGradePoint();
            weightedPoints += gp * credits;
            totalCredits += credits;
        }

        double percentage = totalMax == 0 ? 0 : (totalMarks / totalMax) * 100;
        double cgpa = totalCredits == 0 ? 0 : weightedPoints / totalCredits;

        Map<String, Object> marksheet = new HashMap<>();
        marksheet.put("studentId", studentId);
        marksheet.put("results", results);
        marksheet.put("totalMarks", totalMarks);
        marksheet.put("totalMaxMarks", totalMax);
        marksheet.put("percentage", Math.round(percentage * 100.0) / 100.0);
        marksheet.put("cgpa", Math.round(cgpa * 100.0) / 100.0);
        return marksheet;
    }

    public void delete(String id) {
        Result existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
