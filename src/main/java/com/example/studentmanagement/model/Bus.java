package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "buses")
public class Bus {

    @Id
    private String id;

    private String busNumber;
    private String driverName;
    private String driverPhone;
    private String route;
    private String pickupPoint;
    private Integer capacity;

    public Bus() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getBusNumber() { return busNumber; }
    public void setBusNumber(String busNumber) { this.busNumber = busNumber; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getDriverPhone() { return driverPhone; }
    public void setDriverPhone(String driverPhone) { this.driverPhone = driverPhone; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public String getPickupPoint() { return pickupPoint; }
    public void setPickupPoint(String pickupPoint) { this.pickupPoint = pickupPoint; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

}