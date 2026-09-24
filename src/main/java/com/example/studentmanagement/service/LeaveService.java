package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Leave;
import com.example.studentmanagement.repository.LeaveRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository repository;

    public List<Leave> getAll() {
        return repository.findAll();
    }

    public Leave getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave not found with id: " + id));
    }

    public Leave create(Leave leave) {
        leave.setStatus("PENDING");
        return repository.save(leave);
    }

    public Leave setStatus(String id, String status, String approvedBy) {
        Leave leave = getById(id);
        leave.setStatus(status);
        leave.setApprovedBy(approvedBy);
        return repository.save(leave);
    }

    public Leave update(String id, Leave leave) {
        Leave existing = getById(id);
        leave.setId(existing.getId());
        return repository.save(leave);
    }

    public void delete(String id) {
        Leave existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
