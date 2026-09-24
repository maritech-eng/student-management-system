package com.example.studentmanagement.service;

import com.example.studentmanagement.model.TransportAllocation;
import com.example.studentmanagement.repository.TransportAllocationRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportAllocationService {

    @Autowired
    private TransportAllocationRepository repository;

    public List<TransportAllocation> getAll() {
        return repository.findAll();
    }

    public TransportAllocation getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TransportAllocation not found with id: " + id));
    }

    public TransportAllocation create(TransportAllocation transportAllocation) {
        return repository.save(transportAllocation);
    }

    public TransportAllocation update(String id, TransportAllocation transportAllocation) {
        TransportAllocation existing = getById(id);
        transportAllocation.setId(existing.getId());
        return repository.save(transportAllocation);
    }

    public void delete(String id) {
        TransportAllocation existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
