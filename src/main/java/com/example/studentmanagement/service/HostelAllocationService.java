package com.example.studentmanagement.service;

import com.example.studentmanagement.model.HostelAllocation;
import com.example.studentmanagement.model.Room;
import com.example.studentmanagement.repository.HostelAllocationRepository;
import com.example.studentmanagement.repository.RoomRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class HostelAllocationService {

    @Autowired
    private HostelAllocationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    public List<HostelAllocation> getAll() {
        return repository.findAll();
    }

    public HostelAllocation getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HostelAllocation not found with id: " + id));
    }

    /** Allocates a student to a room: validates bed availability and increments occupiedBeds. */
    public HostelAllocation create(HostelAllocation hostelAllocation) {
        Room room = roomRepository.findById(hostelAllocation.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + hostelAllocation.getRoomId()));

        int capacity = room.getCapacity() == null ? 0 : room.getCapacity();
        int occupied = room.getOccupiedBeds() == null ? 0 : room.getOccupiedBeds();
        if (occupied >= capacity) {
            throw new IllegalArgumentException("This room is already at full capacity");
        }

        room.setOccupiedBeds(occupied + 1);
        roomRepository.save(room);

        hostelAllocation.setAllocationDate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        hostelAllocation.setStatus("ACTIVE");
        return repository.save(hostelAllocation);
    }

    public HostelAllocation update(String id, HostelAllocation hostelAllocation) {
        HostelAllocation existing = getById(id);
        hostelAllocation.setId(existing.getId());
        return repository.save(hostelAllocation);
    }

    /** Removes an allocation and frees up the bed in the room. */
    public void delete(String id) {
        HostelAllocation existing = getById(id);
        roomRepository.findById(existing.getRoomId()).ifPresent(room -> {
            int occupied = room.getOccupiedBeds() == null ? 0 : room.getOccupiedBeds();
            room.setOccupiedBeds(Math.max(occupied - 1, 0));
            roomRepository.save(room);
        });
        repository.deleteById(existing.getId());
    }
}
