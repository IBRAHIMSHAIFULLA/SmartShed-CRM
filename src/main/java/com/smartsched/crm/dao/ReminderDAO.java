package com.smartsched.crm.dao;

import com.smartsched.crm.Database;
import com.smartsched.crm.models.Reminder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReminderDAO {

    // 1️⃣ Fetch active reminders with appointment datetime
    public List<Reminder> fetchActiveReminders() throws SQLException {

        List<Reminder> list = new ArrayList<>();

        String sql = """
            SELECT r.id,
                   r.appointment_id,
                   r.reminder_offset_minutes,
                   r.last_sent_at,
                   r.is_active,
                   a.appointment_date,
                   a.appointment_time
            FROM reminders r
            JOIN appointments a ON r.appointment_id = a.appointment_id
            WHERE r.is_active = TRUE
              AND a.status = 'SCHEDULED'
        """;

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Reminder r = new Reminder();
                r.setId(rs.getInt("id"));
                r.setAppointmentId(rs.getInt("appointment_id"));
                r.setReminderOffsetMinutes(rs.getInt("reminder_offset_minutes"));
                r.setActive(rs.getBoolean("is_active"));

                // last_sent_at (can be null)
                Timestamp lastSent = rs.getTimestamp("last_sent_at");
                if (lastSent != null) {
                    r.setLastSentAt(lastSent.toLocalDateTime());
                }

                // Combine appointment_date + appointment_time
                LocalDateTime appointmentDateTime =
                        rs.getDate("appointment_date")
                          .toLocalDate()
                          .atTime(rs.getTime("appointment_time").toLocalTime());

                r.setAppointmentDateTime(appointmentDateTime);

                list.add(r);
            }
        }
        return list;
    }

    // 2️⃣ Mark reminder as sent
    public void markSent(int reminderId) throws SQLException {

        String sql = "UPDATE reminders SET last_sent_at = NOW() WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reminderId);
            ps.executeUpdate();
        }
    }
}
