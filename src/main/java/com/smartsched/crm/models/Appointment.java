package com.smartsched.crm.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int id;
    private int clientId;
    private String title;
    private String description;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String priority;
    private String status;

    // ===== GETTERS =====
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS =====
    public void setId(int id) {
        this.id = id;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
