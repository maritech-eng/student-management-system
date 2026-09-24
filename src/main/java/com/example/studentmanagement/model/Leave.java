package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "leaves")
public class Leave {

    @Id
    private String id;

    private String applicantId;
    private String applicantType;
    private String fromDate;
    private String toDate;
    private String reason;
    private String status;
    private String approvedBy;

    public Leave() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getApplicantId() { return applicantId; }
    public void setApplicantId(String applicantId) { this.applicantId = applicantId; }

    public String getApplicantType() { return applicantType; }
    public void setApplicantType(String applicantType) { this.applicantType = applicantType; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

}