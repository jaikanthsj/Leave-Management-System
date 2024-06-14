package com.example.SpringSecurity30.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SpringSecurity30.model.EmpDetails;

import java.util.Optional;

public interface EmpDetailsRepository extends JpaRepository<EmpDetails, Integer> {

    Optional<EmpDetails> findByUserId(Integer userId);

}