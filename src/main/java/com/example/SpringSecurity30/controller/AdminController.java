package com.example.SpringSecurity30.controller;

import com.example.SpringSecurity30.model.Leave;
import com.example.SpringSecurity30.model.Policy;
import com.example.SpringSecurity30.service.LeaveService;
import com.example.SpringSecurity30.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/newPolicy")
    public ResponseEntity<Object> addPolicy(@RequestBody Policy policy) {
        try {
            Policy addedPolicy = policyService.addPolicy(policy);
            return ResponseEntity.ok(addedPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updatePolicy/{policyId}")
    public ResponseEntity<Object> updatePolicy(
            @PathVariable Integer policyId,
            @RequestBody Policy policy) {
        try {
            Policy updatedPolicy = policyService.updatePolicy(policyId, policy);
            return ResponseEntity.ok(updatedPolicy);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/current")
    public ResponseEntity<Object> getCurrentPolicy() {
        Policy currentPolicy = policyService.getCurrentPolicy();
        if (currentPolicy != null) {
            return ResponseEntity.ok(currentPolicy);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
