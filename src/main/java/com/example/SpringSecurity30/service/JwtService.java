package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.Util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class JwtService {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String extractUsername(String token) {
        return jwtUtil.extractUsername(token);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return jwtUtil.extractClaim(token, claimsResolver);
    }

    public String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return jwtUtil.isTokenValid(token, userDetails);
    }
}
