package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Exam;
import com.example.studentmanagement.repository.ExamRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository repository;

    public List<Exam> getAll() {
        return repository.findAll();
    }

    public Exam getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found with id: " + id));
    }

    public Exam create(Exam exam) {
        return repository.save(exam);
    }

    public Exam update(String id, Exam exam) {
        Exam existing = getById(id);
        exam.setId(existing.getId());
        return repository.save(exam);
    }

    public void delete(String id) {
        Exam existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
