package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Department;
import com.example.studentmanagement.repository.DepartmentRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAll() {
        return repository.findAll();
    }

    public Department getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department create(Department department) {
        return repository.save(department);
    }

    public Department update(String id, Department department) {
        Department existing = getById(id);
        department.setId(existing.getId());
        return repository.save(department);
    }

    public void delete(String id) {
        Department existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
