package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Staff;
import com.example.studentmanagement.repository.StaffRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository repository;

    public List<Staff> getAll() {
        return repository.findAll();
    }

    public Staff getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
    }

    public Staff create(Staff staff) {
        return repository.save(staff);
    }

    public Staff update(String id, Staff staff) {
        Staff existing = getById(id);
        staff.setId(existing.getId());
        return repository.save(staff);
    }

    public void delete(String id) {
        Staff existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
