package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "faculty")
public class Faculty {

    @Id
    private String id;

    private String facultyCode;
    private String name;
    private String email;
    private String phone;
    private String departmentId;
    private String designation;
    private String qualification;
    private String joiningDate;
    private List<String> subjectsHandled = new java.util.ArrayList<>();

    public Faculty() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFacultyCode() { return facultyCode; }
    public void setFacultyCode(String facultyCode) { this.facultyCode = facultyCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getJoiningDate() { return joiningDate; }
    public void setJoiningDate(String joiningDate) { this.joiningDate = joiningDate; }

    public List<String> getSubjectsHandled() { return subjectsHandled; }
    public void setSubjectsHandled(List<String> subjectsHandled) { this.subjectsHandled = subjectsHandled; }

}