package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Timetable;
import com.example.studentmanagement.repository.TimetableRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository repository;

    public List<Timetable> getAll() {
        return repository.findAll();
    }

    public Timetable getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Timetable not found with id: " + id));
    }

    public Timetable create(Timetable timetable) {
        return repository.save(timetable);
    }

    public Timetable update(String id, Timetable timetable) {
        Timetable existing = getById(id);
        timetable.setId(existing.getId());
        return repository.save(timetable);
    }

    public void delete(String id) {
        Timetable existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
