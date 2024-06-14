package com.example.SpringSecurity30.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmpDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer sl; // Sick Leave balance
    private Integer cl; // Casual Leave balance
    private Integer el;
    private Integer ol;
    private Integer sl_perMonth;
    private Integer cl_perMonth;

    public Integer getSl_perMonth() {
        return sl_perMonth;
    }

    public void setSl_perMonth(Integer sl_perMonth) {
        this.sl_perMonth = sl_perMonth;
    }

    public Integer getCl_perMonth() {
        return cl_perMonth;
    }

    public void setCl_perMonth(Integer cl_perMonth) {
        this.cl_perMonth = cl_perMonth;
    }

    public Integer getOl() {
        return ol;
    }

    public void setOl(Integer ol) {
        this.ol = ol;
    }

    // Earned Leave balance

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Integer getCl() {
        return cl;
    }

    public void setCl(Integer cl) {
        this.cl = cl;
    }

    public Integer getEl() {
        return el;
    }

    public void setEl(Integer el) {
        this.el = el;
    }
}
