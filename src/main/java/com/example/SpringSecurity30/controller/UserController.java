package com.example.SpringSecurity30.controller;

import com.example.SpringSecurity30.Util.JwtUtil;
import com.example.SpringSecurity30.dto.LoginUserDto;
import com.example.SpringSecurity30.dto.RegisterUserDto;
import com.example.SpringSecurity30.model.EmpDetails;
import com.example.SpringSecurity30.model.LoginResponse;
import com.example.SpringSecurity30.model.UserModel;
import com.example.SpringSecurity30.repository.EmpDetailsRepository;
import com.example.SpringSecurity30.service.AuthenticationService;
import com.example.SpringSecurity30.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequestMapping("/auth")
@RestController
public class UserController {

    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    EmpDetailsRepository empDetailsRepository;


    @PostMapping("/signup")
    public ResponseEntity<UserModel> register(@RequestBody RegisterUserDto registerUserDto) {
        UserModel registeredUser = authenticationService.signup(registerUserDto);

        // Check if the registered user has the role "ROLE_USER"
        if (registeredUser != null && Objects.equals(registeredUser.getRoles(), "ROLE_USER")) {
            // Create and save EmpDetails
            EmpDetails empDetails = new EmpDetails();
            empDetails.setUserId(registeredUser.getId());
            empDetails.setSl(12);
            empDetails.setCl(12);
            empDetails.setEl(12);
            empDetails.setOl(4);
            empDetails.setCl_perMonth(1);
            empDetails.setSl_perMonth(1);
            empDetailsRepository.save(empDetails); // Save EmpDetails to the database
        }

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserModel authenticatedUser = authenticationService.authenticate(loginUserDto);
        System.out.println("Authenticated user------>>>>>>: " + loginUserDto.getEmail());
        String role = authenticatedUser.getRoles();
        System.out.println("Role------>>>>>>: " + role);
        if (Objects.equals(role, "ROLE_ADMIN")) {
            String jwtToken = jwtService.generateToken(authenticatedUser);

            // Calculate expiration time as needed
            long expirationTime = System.currentTimeMillis() + jwtUtil.getJwtExpiration();

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            return ResponseEntity.ok(loginResponse);
        } else {
            return ResponseEntity.status(200).build();
        }
    }
}
