package com.example.SpringSecurity30.controller;

import com.example.SpringSecurity30.model.Leave;
import com.example.SpringSecurity30.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> applyForLeave(@RequestBody Leave leave) {
        String result = leaveService.applyForLeave(leave);
        if (result.startsWith("Error:")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
    }

    @PostMapping("/approve/{leaveId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> approveLeave(@PathVariable Integer leaveId) {
        try {
            leaveService.approveLeave(leaveId);
            return ResponseEntity.ok("Leave approved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Leave is not pending approval");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to approve leave");
        }
    }

    @PostMapping("/reject/{leaveId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> rejectLeave(@PathVariable Integer leaveId) {
        try {
            leaveService.rejectLeave(leaveId);
            return ResponseEntity.ok("Leave rejected successfully");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave not found");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Leave is not pending approval");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reject leave");
        }
    }

    @GetMapping("/generate-csv/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Resource> generateCsv(@PathVariable Integer userId) {
        try {
            File csvFile = new File(leaveService.generateCsvForUser(userId));
            FileSystemResource resource = new FileSystemResource(csvFile);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFile.getName()); // This line triggers the download prompt
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(csvFile.length())
                    .body(resource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

}
