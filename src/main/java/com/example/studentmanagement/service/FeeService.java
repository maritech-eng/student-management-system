package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Fee;
import com.example.studentmanagement.repository.FeeRepository;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

    @Autowired
    private FeeRepository repository;

    public List<Fee> getAll() {
        return repository.findAll();
    }

    public Fee getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found with id: " + id));
    }

    public Fee create(Fee fee) {
        applyCalculations(fee);
        return repository.save(fee);
    }

    public Fee update(String id, Fee fee) {
        Fee existing = getById(id);
        fee.setId(existing.getId());
        applyCalculations(fee);
        return repository.save(fee);
    }

    public List<Fee> getByStudent(String studentId) {
        return repository.findAll().stream()
                .filter(f -> studentId.equals(f.getStudentId()))
                .toList();
    }

    /**
     * Total fee = sum of the individual heads (if not explicitly provided),
     * pending amount = total - paid, and payment status is derived
     * automatically so the frontend never has to compute this itself.
     */
    private void applyCalculations(Fee fee) {
        double tuition = nz(fee.getTuitionFee());
        double exam = nz(fee.getExamFee());
        double hostel = nz(fee.getHostelFee());
        double transport = nz(fee.getTransportFee());
        double other = nz(fee.getOtherFee());
        double computedTotal = tuition + exam + hostel + transport + other;

        double total = fee.getTotalFee() != null && fee.getTotalFee() > 0 ? fee.getTotalFee() : computedTotal;
        double paid = nz(fee.getPaidAmount());
        double pending = Math.max(total - paid, 0);

        fee.setTotalFee(total);
        fee.setPendingAmount(pending);

        if (pending <= 0 && total > 0) {
            fee.setPaymentStatus("PAID");
        } else if (paid > 0) {
            fee.setPaymentStatus("PARTIAL");
        } else {
            fee.setPaymentStatus("PENDING");
        }
    }

    private double nz(Double v) {
        return v == null ? 0 : v;
    }

    public void delete(String id) {
        Fee existing = getById(id);
        repository.deleteById(existing.getId());
    }
}
