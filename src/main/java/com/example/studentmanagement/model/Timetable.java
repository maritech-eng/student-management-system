package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "timetables")
public class Timetable {

    @Id
    private String id;

    private String departmentId;
    private String courseId;
    private Integer semester;
    private String section;
    private String day;
    private String time;
    private String subjectId;
    private String facultyId;
    private String roomNumber;

    public Timetable() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public String getDay() { return day; }
    public void setDay(String day) { this.day = day; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }

    public String getFacultyId() { return facultyId; }
    public void setFacultyId(String facultyId) { this.facultyId = facultyId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

}