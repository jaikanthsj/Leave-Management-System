package com.example.SpringSecurity30.repository;

import com.example.SpringSecurity30.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    // additional query methods if needed
}
