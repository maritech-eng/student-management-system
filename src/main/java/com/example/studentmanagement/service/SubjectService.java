package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Subject;
import com.example.studentmanagement.repository.SubjectRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public List<Subject> getAll() {
        return repository.findAll();
    }

    public Subject getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }

    public Subject create(Subject subject) {
        return repository.save(subject);
    }

    public Subject update(String id, Subject subject) {
        Subject existing = getById(id);
        subject.setId(existing.getId());
        return repository.save(subject);
    }

    public void delete(String id) {
        Subject existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
