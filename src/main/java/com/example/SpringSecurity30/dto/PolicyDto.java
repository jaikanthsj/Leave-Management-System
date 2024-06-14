package com.example.SpringSecurity30.dto;

public class PolicyDto {

    private String name;
    private String description;
    private boolean isActive;
    // Add more fields as needed based on your policy definition

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
