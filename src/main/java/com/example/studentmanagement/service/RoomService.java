package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Room;
import com.example.studentmanagement.repository.RoomRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public List<Room> getAll() {
        return repository.findAll();
    }

    public Room getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    public Room create(Room room) {
        return repository.save(room);
    }

    public Room update(String id, Room room) {
        Room existing = getById(id);
        room.setId(existing.getId());
        return repository.save(room);
    }

    public void delete(String id) {
        Room existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
