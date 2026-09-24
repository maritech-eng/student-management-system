package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(String id, Student student) {
        Student existing = getById(id);
        student.setId(existing.getId());
        return repository.save(student);
    }

    public void delete(String id) {
        Student existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
