package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Notice;
import com.example.studentmanagement.repository.NoticeRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository repository;

    public List<Notice> getAll() {
        return repository.findAll();
    }

    public Notice getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notice not found with id: " + id));
    }

    public Notice create(Notice notice) {
        return repository.save(notice);
    }

    public Notice update(String id, Notice notice) {
        Notice existing = getById(id);
        notice.setId(existing.getId());
        return repository.save(notice);
    }

    public void delete(String id) {
        Notice existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
