package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "placements")
public class Placement {

    @Id
    private String id;

    private String companyName;
    private String jobRole;
    private String packageOffered;
    private String eligibility;
    private String driveDate;
    private String location;
    private String description;

    public Placement() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getJobRole() { return jobRole; }
    public void setJobRole(String jobRole) { this.jobRole = jobRole; }

    public String getPackageOffered() { return packageOffered; }
    public void setPackageOffered(String packageOffered) { this.packageOffered = packageOffered; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

    public String getDriveDate() { return driveDate; }
    public void setDriveDate(String driveDate) { this.driveDate = driveDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}