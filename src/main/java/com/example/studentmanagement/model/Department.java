package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "departments")
public class Department {

    @Id
    private String id;

    private String departmentCode;
    private String name;
    private String hodName;
    private String description;

    public Department() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHodName() { return hodName; }
    public void setHodName(String hodName) { this.hodName = hodName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}