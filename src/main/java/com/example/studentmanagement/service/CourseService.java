package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Course;
import com.example.studentmanagement.repository.CourseRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    public Course create(Course course) {
        return repository.save(course);
    }

    public Course update(String id, Course course) {
        Course existing = getById(id);
        course.setId(existing.getId());
        return repository.save(course);
    }

    public void delete(String id) {
        Course existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
