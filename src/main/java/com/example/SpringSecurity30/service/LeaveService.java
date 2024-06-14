package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.model.EmpDetails;
import com.example.SpringSecurity30.model.Leave;
import com.example.SpringSecurity30.repository.EmpDetailsRepository;
import com.example.SpringSecurity30.repository.LeaveRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmpDetailsRepository empDetailsRepository;

    public String applyForLeave(Leave leave) {
        // Example validations based on policy conditions

        // Example: CL must be created before 5 working days
        if ("CL".equals(leave.getType())) {
            LocalDate today = LocalDate.now();
            LocalDate fiveWorkingDaysBeforeStartDate = leave.getStartDate().minusDays(5); // Adjust this logic based on your actual business days calculation
            if (today.isAfter(fiveWorkingDaysBeforeStartDate)) {
                return "CL must be created before 5 working days.";
            }
        }

        // Example: SL must be taken in case of emergency


        if ("OL".equals(leave.getType())) {
            LocalDate today = LocalDate.now();
            LocalDate fiveWorkingDaysBeforeStartDate = leave.getStartDate().minusDays(10); // Adjust this logic based on your actual business days calculation
            if (today.isAfter(fiveWorkingDaysBeforeStartDate)) {
                return "EL must be created before 10 working days.";
            }
        }

        if ("SL".equals(leave.getType())) {
            LocalDate today = LocalDate.now();
            LocalDate fiveWorkingDaysBeforeStartDate = leave.getStartDate().minusDays(1); // Adjust this logic based on your actual business days calculation
            if (today.isAfter(fiveWorkingDaysBeforeStartDate)) {
                return "SL can be applied in emergency cases";
            }
        }
        leaveRepository.save(leave);
        return "Leave applied successfully.";
    }

    public void approveLeave(Integer leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Leave not found"));

        if (!leave.getStatus().equals("PENDING")) {
            throw new IllegalStateException("Leave is not pending approval");
        }

        leave.setStatus("APPROVED");
        leaveRepository.save(leave);

        empDetailsRepository.findByUserId(leave.getUserId())
                .ifPresent(empDetails -> {
                    switch (leave.getType()) {
                        case "SL":
                            empDetails.setSl(empDetails.getSl() - calculateLeaveDays(leave.getStartDate(), leave.getEndDate()));
                            break;
                        case "CL":
                            empDetails.setCl(empDetails.getCl() - calculateLeaveDays(leave.getStartDate(), leave.getEndDate()));
                            break;
                        case "EL":
                            empDetails.setEl(empDetails.getEl() - calculateLeaveDays(leave.getStartDate(), leave.getEndDate()));
                            break;
                        case "OL":
                            empDetails.setOl(empDetails.getOl()- calculateLeaveDays(leave.getStartDate(), leave.getEndDate()));
                            break;
                        default:
                            // Handle unexpected leave types, if necessary
                            break;
                    }
                    empDetailsRepository.save(empDetails);
                });

    }

    public void rejectLeave(Integer leaveId) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() -> new IllegalArgumentException("Leave not found"));

        if (!leave.getStatus().equals("PENDING")) {
            throw new IllegalStateException("Leave is not pending approval");
        }

        // Update leave status to rejected
        leave.setStatus("REJECTED");
        leaveRepository.save(leave);
    }

    private int calculateLeaveDays(LocalDate startDate, LocalDate endDate) {
        // Logic to calculate number of leave days between start and end date
        // Implement your logic here based on your requirements
        // For simplicity, let's assume this calculates the number of days between two dates
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public String generateCsvForUser(Integer userId) throws IOException {
        Optional<EmpDetails> empDetailsOpt = empDetailsRepository.findByUserId(userId);

        if (empDetailsOpt.isPresent()) {
            EmpDetails empDetails = empDetailsOpt.get();
            String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String fileName = getString(userId, todayDate, empDetails);

            return fileName;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    private static String getString(Integer userId, String todayDate, EmpDetails empDetails) throws IOException {
        String fileName = userId + "_" + todayDate + ".csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            // Write header
            String[] header = { "UserId", "SL", "CL", "EL", "OL" };
            writer.writeNext(header);

            // Write user details
            String[] data = {
                    empDetails.getUserId().toString(),
                    empDetails.getSl().toString(),
                    empDetails.getCl().toString(),
                    empDetails.getEl().toString(),
                    empDetails.getOl().toString()
            };
            writer.writeNext(data);
        }
        return fileName;
    }
}
