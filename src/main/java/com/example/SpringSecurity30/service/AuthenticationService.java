package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.dto.LoginUserDto;
import com.example.SpringSecurity30.dto.RegisterUserDto;
import com.example.SpringSecurity30.model.UserModel;
import com.example.SpringSecurity30.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


    public UserModel signup(RegisterUserDto input) {
        try {
            UserModel user = new UserModel();
            user.setFullName(input.getFullName());
            user.setEmail(input.getEmail());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setRoles(input.getRole());

            return userRepository.save(user);
        } catch (Exception e) {
            // Log the error
            logger.error("Error occurred during user signup: " + e.getMessage());
            // Rethrow the exception or handle it appropriately
            throw e;
        }
    }

    public UserModel authenticate(LoginUserDto input) {
        UserModel user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }
}
