package com.example.SpringSecurity30.controller;

import com.example.SpringSecurity30.dto.CalendarEventDTO;
import com.example.SpringSecurity30.model.CalendarDay;
import com.example.SpringSecurity30.model.CalendarEvent;
import com.example.SpringSecurity30.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/events")
    public ResponseEntity<Optional<CalendarEvent>> getEventsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<CalendarEvent> events = calendarService.getEventsByDate(date);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/month/{year}/{month}")
    public ResponseEntity<List<CalendarDay>> getCalendarMonth(
            @PathVariable int year,
            @PathVariable int month) {
        List<CalendarDay> calendarMonth = calendarService.getCalendarMonth(year, month);
        return ResponseEntity.ok(calendarMonth);
    }

    @PostMapping("/events")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addOrUpdateEvent(@RequestBody CalendarEventDTO request) {
        calendarService.addOrUpdateEvent(request.getDate(), request.getDescription());
        return ResponseEntity.ok("Event updated successfully");
    }

    @DeleteMapping("/events/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        calendarService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}
