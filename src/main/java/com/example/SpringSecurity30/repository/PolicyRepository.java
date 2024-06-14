package com.example.SpringSecurity30.repository;

import com.example.SpringSecurity30.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    // Add custom query methods if needed
}
