package com.example.studentmanagement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "fees")
public class Fee {

    @Id
    private String id;

    private String studentId;
    private String academicYear;
    private Integer semester;
    private Double tuitionFee;
    private Double examFee;
    private Double hostelFee;
    private Double transportFee;
    private Double otherFee;
    private Double totalFee;
    private Double paidAmount;
    private Double pendingAmount;
    private String paymentDate;
    private String paymentStatus;

    public Fee() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Double getTuitionFee() { return tuitionFee; }
    public void setTuitionFee(Double tuitionFee) { this.tuitionFee = tuitionFee; }

    public Double getExamFee() { return examFee; }
    public void setExamFee(Double examFee) { this.examFee = examFee; }

    public Double getHostelFee() { return hostelFee; }
    public void setHostelFee(Double hostelFee) { this.hostelFee = hostelFee; }

    public Double getTransportFee() { return transportFee; }
    public void setTransportFee(Double transportFee) { this.transportFee = transportFee; }

    public Double getOtherFee() { return otherFee; }
    public void setOtherFee(Double otherFee) { this.otherFee = otherFee; }

    public Double getTotalFee() { return totalFee; }
    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }

    public Double getPaidAmount() { return paidAmount; }
    public void setPaidAmount(Double paidAmount) { this.paidAmount = paidAmount; }

    public Double getPendingAmount() { return pendingAmount; }
    public void setPendingAmount(Double pendingAmount) { this.pendingAmount = pendingAmount; }

    public String getPaymentDate() { return paymentDate; }
    public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

}