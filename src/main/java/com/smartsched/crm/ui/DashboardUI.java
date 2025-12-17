package com.smartsched.crm.ui;

import java.sql.*;
import com.smartsched.crm.Database;

public class DashboardUI {

    public void show() {
        try (Connection con = Database.getConnection()) {

            // Total Clients
            Statement st1 = con.createStatement();
            ResultSet c1 = st1.executeQuery("SELECT COUNT(*) AS total FROM clients");
            c1.next();
            int totalClients = c1.getInt("total");

            // Today's Appointments
            Statement st2 = con.createStatement();
            ResultSet c2 = st2.executeQuery(
                "SELECT COUNT(*) AS todayCount FROM appointments WHERE appointment_date = CURDATE()"
            );
            c2.next();
            int todayAppointments = c2.getInt("todayCount");

            // Pending Reminders
            Statement st3 = con.createStatement();
            ResultSet c3 = st3.executeQuery(
                "SELECT COUNT(*) AS pending FROM reminders WHERE is_active = TRUE AND last_sent_at IS NULL"
            );
            c3.next();
            int pendingReminders = c3.getInt("pending");

            System.out.println("\n=== Dashboard ===");
            System.out.println("Total Clients        : " + totalClients);
            System.out.println("Today's Appointments : " + todayAppointments);
            System.out.println("Pending Reminders    : " + pendingReminders);
            System.out.println("====================\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
