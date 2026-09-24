package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "rooms")
public class Room {

    @Id
    private String id;

    private String hostelId;
    private String roomNumber;
    private Integer capacity;
    private Integer occupiedBeds;

    public Room() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHostelId() { return hostelId; }
    public void setHostelId(String hostelId) { this.hostelId = hostelId; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getOccupiedBeds() { return occupiedBeds; }
    public void setOccupiedBeds(Integer occupiedBeds) { this.occupiedBeds = occupiedBeds; }

}