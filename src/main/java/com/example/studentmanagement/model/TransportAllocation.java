package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "transport_allocations")
public class TransportAllocation {

    @Id
    private String id;

    private String studentId;
    private String busId;
    private String allocationDate;

    public TransportAllocation() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getBusId() { return busId; }
    public void setBusId(String busId) { this.busId = busId; }

    public String getAllocationDate() { return allocationDate; }
    public void setAllocationDate(String allocationDate) { this.allocationDate = allocationDate; }

}