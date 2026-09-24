package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "exams")
public class Exam {

    @Id
    private String id;

    private String examName;
    private String subjectId;
    private Integer semester;
    private String examDate;
    private Double maxMarks;

    public Exam() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getExamDate() { return examDate; }
    public void setExamDate(String examDate) { this.examDate = examDate; }

    public Double getMaxMarks() { return maxMarks; }
    public void setMaxMarks(Double maxMarks) { this.maxMarks = maxMarks; }

}