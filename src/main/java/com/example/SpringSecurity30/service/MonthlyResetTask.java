package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.model.EmpDetails;
import com.example.SpringSecurity30.repository.EmpDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MonthlyResetTask {

    @Autowired
    private EmpDetailsRepository empDetailsRepository;

    @Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the first day of each month
    public void resetSickLeavePerMonth() {
        List<EmpDetails> employees = empDetailsRepository.findAll();
        for (EmpDetails emp : employees) {
            emp.setSl_perMonth(1);
            emp.setCl_perMonth(1);
            empDetailsRepository.save(emp);
        }
    }
}
