package com.example.SpringSecurity30.model;

import java.time.LocalDate;

public class CalendarDay {

    private LocalDate date;
    private String description;

    // Constructors, getters, and setters
    public CalendarDay(LocalDate date, String description) {
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

