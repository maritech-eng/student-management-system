package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Faculty;
import com.example.studentmanagement.repository.FacultyRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private FacultyRepository repository;

    public List<Faculty> getAll() {
        return repository.findAll();
    }

    public Faculty getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty not found with id: " + id));
    }

    public Faculty create(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty update(String id, Faculty faculty) {
        Faculty existing = getById(id);
        faculty.setId(existing.getId());
        return repository.save(faculty);
    }

    public void delete(String id) {
        Faculty existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
