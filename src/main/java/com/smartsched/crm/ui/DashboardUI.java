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

            // ✅ Missed Appointments (NEW)
            Statement st4 = con.createStatement();
            ResultSet c4 = st4.executeQuery(
                "SELECT COUNT(*) AS missed FROM appointments " +
                "WHERE status = 'SCHEDULED' " +
                "AND TIMESTAMP(appointment_date, appointment_time) < NOW()"
            );
            c4.next();
            int missedAppointments = c4.getInt("missed");

            // ✅ Upcoming Appointments (next 24 hours)
            Statement st5 = con.createStatement();
            ResultSet c5 = st5.executeQuery(
                "SELECT COUNT(*) AS upcoming FROM appointments " +
                "WHERE status = 'SCHEDULED' " +
                "AND TIMESTAMP(appointment_date, appointment_time) BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 1 DAY)"
            );
            c5.next();
            int upcomingAppointments = c5.getInt("upcoming");

            System.out.println("\n=== Dashboard ===");
            System.out.println("Total Clients        : " + totalClients);
            System.out.println("Today's Appointments : " + todayAppointments);
            System.out.println("Upcoming Appointments: " + upcomingAppointments);
            System.out.println("Missed Appointments  : " + missedAppointments);
            System.out.println("Pending Reminders    : " + pendingReminders);
            System.out.println("====================\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
