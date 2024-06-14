package com.example.SpringSecurity30.dto;

import java.time.LocalDate;

public class CalendarEventDTO {

    private LocalDate date;
    private String description;

    // Constructors, getters, and setters
    public CalendarEventDTO(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
