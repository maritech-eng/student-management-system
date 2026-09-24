package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "attendance")
public class Attendance {

    @Id
    private String id;

    private String studentId;
    private String subjectId;
    private String facultyId;
    private String date;
    private Integer semester;
    private String status;

    public Attendance() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}