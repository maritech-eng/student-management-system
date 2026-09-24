package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Book;
import com.example.studentmanagement.repository.BookRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book create(Book book) {
        return repository.save(book);
    }

    public Book update(String id, Book book) {
        Book existing = getById(id);
        book.setId(existing.getId());
        return repository.save(book);
    }

    public void delete(String id) {
        Book existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
