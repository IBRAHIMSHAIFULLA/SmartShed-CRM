package com.smartsched.crm.dao;

import com.smartsched.crm.Database;
import com.smartsched.crm.models.Appointment;
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class AppointmentDAO {

    public void addAppointment(Appointment a) throws SQLException {
        String sql = """
        INSERT INTO appointments
        (client_id, title, description, appointment_date, appointment_time, priority, status)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getClientId());
            ps.setString(2, a.getTitle());
            ps.setString(3, a.getDescription());
            ps.setDate(4, Date.valueOf(a.getAppointmentDate()));
            ps.setTime(5, Time.valueOf(a.getAppointmentTime()));
            ps.setString(6, a.getPriority());
            ps.setString(7, "SCHEDULED");
            ps.executeUpdate();
        }
    }
    public List<Appointment> getAllAppointments() throws SQLException {
    List<Appointment> list = new ArrayList<>();

    String sql = """
        SELECT appointment_id, client_id, title, description,
               appointment_date, appointment_time, priority, status
        FROM appointments
        ORDER BY appointment_date, appointment_time
    """;

    try (Connection con = Database.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Appointment a = new Appointment();
            a.setId(rs.getInt("appointment_id"));
            a.setClientId(rs.getInt("client_id"));
            a.setTitle(rs.getString("title"));
            a.setDescription(rs.getString("description"));
            a.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
            a.setAppointmentTime(rs.getTime("appointment_time").toLocalTime());
            a.setPriority(rs.getString("priority"));
            a.setStatus(rs.getString("status"));
            list.add(a);
        }
    }
    return list;
}
// Cancel appointment
public void cancelAppointment(int appointmentId) throws SQLException {
    String sql = "UPDATE appointments SET status = 'CANCELLED' WHERE appointment_id = ?";

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, appointmentId);
        ps.executeUpdate();
    }
}
public List<Appointment> getMissedAppointments() throws SQLException {
    List<Appointment> list = new ArrayList<>();

    String sql = """
        SELECT *
        FROM appointments
        WHERE status = 'SCHEDULED'
        AND TIMESTAMP(appointment_date, appointment_time) < NOW()
    """;

    try (Connection con = Database.getConnection();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        while (rs.next()) {
            Appointment a = new Appointment();
            a.setId(rs.getInt("appointment_id"));
            a.setClientId(rs.getInt("client_id"));
            a.setTitle(rs.getString("title"));
            a.setAppointmentDate(rs.getDate("appointment_date").toLocalDate());
            a.setAppointmentTime(rs.getTime("appointment_time").toLocalTime());
            a.setStatus(rs.getString("status"));
            list.add(a);
        }
    }
    return list;
}
// Delete cancelled appointments of a client
public void deleteCancelledAppointmentsByClient(int clientId) throws SQLException {
    String sql = "DELETE FROM appointments WHERE client_id = ? AND status = 'CANCELLED'";

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, clientId);
        ps.executeUpdate();
    }
}
public void markMissedAppointments() throws SQLException {
    String sql = """
        UPDATE appointments
        SET status = 'MISSED'
        WHERE status = 'SCHEDULED'
          AND TIMESTAMP(appointment_date, appointment_time) < NOW()
    """;

    try (Connection con = Database.getConnection();
         Statement st = con.createStatement()) {
        st.executeUpdate(sql);
    }
}

}
