package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Bus;
import com.example.studentmanagement.repository.BusRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository repository;

    public List<Bus> getAll() {
        return repository.findAll();
    }

    public Bus getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + id));
    }

    public Bus create(Bus bus) {
        return repository.save(bus);
    }

    public Bus update(String id, Bus bus) {
        Bus existing = getById(id);
        bus.setId(existing.getId());
        return repository.save(bus);
    }

    public void delete(String id) {
        Bus existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
