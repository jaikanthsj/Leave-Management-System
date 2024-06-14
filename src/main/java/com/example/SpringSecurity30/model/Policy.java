package com.example.SpringSecurity30.model;

import jakarta.persistence.*;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String totLeave;
    private String condition;

    public String getTotLeave() {
        return totLeave;
    }

    public void setTotLeave(String totLeave) {
        this.totLeave = totLeave;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
