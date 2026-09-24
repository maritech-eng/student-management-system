package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Hostel;
import com.example.studentmanagement.repository.HostelRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {

    @Autowired
    private HostelRepository repository;

    public List<Hostel> getAll() {
        return repository.findAll();
    }

    public Hostel getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hostel not found with id: " + id));
    }

    public Hostel create(Hostel hostel) {
        return repository.save(hostel);
    }

    public Hostel update(String id, Hostel hostel) {
        Hostel existing = getById(id);
        hostel.setId(existing.getId());
        return repository.save(hostel);
    }

    public void delete(String id) {
        Hostel existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
