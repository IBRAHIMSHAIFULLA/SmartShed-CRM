package com.smartsched.crm.models;

import java.time.LocalDateTime;

public class Reminder {

    private int id;
    private int appointmentId;
    private LocalDateTime appointmentDateTime;
    private int reminderOffsetMinutes;
    private LocalDateTime lastSentAt;
    private boolean isActive;

    // ===== GETTERS =====
    public int getId() {
        return id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public int getReminderOffsetMinutes() {
        return reminderOffsetMinutes;
    }

    public LocalDateTime getLastSentAt() {
        return lastSentAt;
    }

    public boolean isActive() {
        return isActive;
    }

    // ===== SETTERS =====
    public void setId(int id) {
        this.id = id;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public void setReminderOffsetMinutes(int reminderOffsetMinutes) {
        this.reminderOffsetMinutes = reminderOffsetMinutes;
    }

    public void setLastSentAt(LocalDateTime lastSentAt) {
        this.lastSentAt = lastSentAt;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
