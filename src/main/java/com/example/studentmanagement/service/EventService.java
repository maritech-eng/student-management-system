package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Event;
import com.example.studentmanagement.repository.EventRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));
    }

    public Event create(Event event) {
        return repository.save(event);
    }

    public Event update(String id, Event event) {
        Event existing = getById(id);
        event.setId(existing.getId());
        return repository.save(event);
    }

    public void delete(String id) {
        Event existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
