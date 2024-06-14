package com.example.SpringSecurity30.repository;


import com.example.SpringSecurity30.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByFullName(String fullName);
}