package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.repository.EmpDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SpringSecurity30.model.EmpDetails;

@Service
public class EmpDetailsService {

    private final EmpDetailsRepository empDetailsRepository;

    @Autowired
    public EmpDetailsService(EmpDetailsRepository empDetailsRepository) {
        this.empDetailsRepository = empDetailsRepository;
    }

    public EmpDetails saveEmpDetails(EmpDetails empDetails) {
        return empDetailsRepository.save(empDetails);
    }
}

