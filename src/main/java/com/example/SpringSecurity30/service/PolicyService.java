package com.example.SpringSecurity30.service;

import com.example.SpringSecurity30.model.Policy;
import com.example.SpringSecurity30.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy addPolicy(Policy policy) {
//        validatePolicy(policy);
        return policyRepository.save(policy);
    }

    public Policy updatePolicy(Integer Id, Policy policy) {
        Policy existingPolicy = policyRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Policy not found"));

        // Update fields based on policy update conditions
        existingPolicy.setType(policy.getType());
        existingPolicy.setTotLeave(policy.getTotLeave());
        existingPolicy.setCondition(policy.getCondition());

//        validatePolicy(existingPolicy);

        return policyRepository.save(existingPolicy);
    }

    public Policy getCurrentPolicy() {
        // For simplicity, assume there is only one current policy or handle versioning
        List<Policy> policies = policyRepository.findAll();
        return policies.isEmpty() ? null : policies.get(0);
    }

//    private void validatePolicy(Policy policy) {
//        // Implement policy validation based on rules for CL, SL, EL
//        // Example validations:
//        if (policy.getCl() < 0 || policy.getCl() > 1) {
//            throw new IllegalArgumentException("Maximum CL per month must be 0 or 1");
//        }
//        if (policy.getSl() < 0 || policy.getSl() > 1) {
//            throw new IllegalArgumentException("Maximum SL per month must be 0 or 1");
//        }
//    }
}
