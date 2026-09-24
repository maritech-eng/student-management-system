package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "hostel_allocations")
public class HostelAllocation {

    @Id
    private String id;

    private String studentId;
    private String roomId;
    private String allocationDate;
    private String status;

    public HostelAllocation() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getAllocationDate() { return allocationDate; }
    public void setAllocationDate(String allocationDate) { this.allocationDate = allocationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}