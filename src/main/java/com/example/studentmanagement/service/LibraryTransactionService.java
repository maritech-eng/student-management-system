package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Book;
import com.example.studentmanagement.model.LibraryTransaction;
import com.example.studentmanagement.repository.BookRepository;
import com.example.studentmanagement.repository.LibraryTransactionRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LibraryTransactionService {

    private static final double FINE_PER_DAY = 5.0;

    @Autowired
    private LibraryTransactionRepository repository;

    @Autowired
    private BookRepository bookRepository;

    public List<LibraryTransaction> getAll() {
        return repository.findAll();
    }

    public LibraryTransaction getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LibraryTransaction not found with id: " + id));
    }

    public LibraryTransaction create(LibraryTransaction libraryTransaction) {
        return repository.save(libraryTransaction);
    }

    public LibraryTransaction update(String id, LibraryTransaction libraryTransaction) {
        LibraryTransaction existing = getById(id);
        libraryTransaction.setId(existing.getId());
        return repository.save(libraryTransaction);
    }

    public void delete(String id) {
        LibraryTransaction existing = getById(id);
        repository.deleteById(existing.getId());
    }

    /** Issues a book: checks availability, decrements stock, opens a transaction with a 14-day due date. */
    public LibraryTransaction issueBook(String bookId, String studentId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (book.getAvailableQuantity() == null || book.getAvailableQuantity() <= 0) {
            throw new IllegalArgumentException("No copies of this book are currently available");
        }

        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);

        LocalDate today = LocalDate.now();
        LibraryTransaction transaction = new LibraryTransaction();
        transaction.setBookId(bookId);
        transaction.setStudentId(studentId);
        transaction.setIssueDate(today.format(DateTimeFormatter.ISO_DATE));
        transaction.setDueDate(today.plusDays(14).format(DateTimeFormatter.ISO_DATE));
        transaction.setStatus("ISSUED");
        transaction.setFine(0.0);
        return repository.save(transaction);
    }

    /** Returns a book: restores stock, computes a late fine if returned after the due date. */
    public LibraryTransaction returnBook(String transactionId) {
        LibraryTransaction transaction = getById(transactionId);
        if ("RETURNED".equalsIgnoreCase(transaction.getStatus())) {
            throw new IllegalArgumentException("This book has already been returned");
        }

        LocalDate today = LocalDate.now();
        transaction.setReturnDate(today.format(DateTimeFormatter.ISO_DATE));
        transaction.setStatus("RETURNED");

        double fine = 0;
        if (transaction.getDueDate() != null) {
            LocalDate due = LocalDate.parse(transaction.getDueDate());
            long lateDays = ChronoUnit.DAYS.between(due, today);
            if (lateDays > 0) {
                fine = lateDays * FINE_PER_DAY;
            }
        }
        transaction.setFine(fine);

        bookRepository.findById(transaction.getBookId()).ifPresent(book -> {
            int available = book.getAvailableQuantity() == null ? 0 : book.getAvailableQuantity();
            int quantity = book.getQuantity() == null ? available + 1 : book.getQuantity();
            book.setAvailableQuantity(Math.min(available + 1, quantity));
            bookRepository.save(book);
        });

        return repository.save(transaction);
    }
}
