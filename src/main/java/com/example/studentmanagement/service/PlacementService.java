package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Placement;
import com.example.studentmanagement.repository.PlacementRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementService {

    @Autowired
    private PlacementRepository repository;

    public List<Placement> getAll() {
        return repository.findAll();
    }

    public Placement getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Placement not found with id: " + id));
    }

    public Placement create(Placement placement) {
        return repository.save(placement);
    }

    public Placement update(String id, Placement placement) {
        Placement existing = getById(id);
        placement.setId(existing.getId());
        return repository.save(placement);
    }

    public void delete(String id) {
        Placement existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
