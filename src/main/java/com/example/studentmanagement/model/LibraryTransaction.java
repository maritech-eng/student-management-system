package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "library_transactions")
public class LibraryTransaction {

    @Id
    private String id;

    private String bookId;
    private String studentId;
    private String issueDate;
    private String dueDate;
    private String returnDate;
    private Double fine;
    private String status;

    public LibraryTransaction() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    public Double getFine() { return fine; }
    public void setFine(Double fine) { this.fine = fine; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}