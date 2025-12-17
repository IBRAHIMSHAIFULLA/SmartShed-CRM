package com.smartsched.crm.services;

import com.smartsched.crm.dao.ReminderDAO;
import com.smartsched.crm.models.Reminder;

import java.time.LocalDateTime;
import java.util.List;

public class ReminderService {

    private ReminderDAO dao = new ReminderDAO();

    public void scanAndSendReminders() {
        try {
            LocalDateTime now = LocalDateTime.now();
            List<Reminder> reminders = dao.fetchActiveReminders();

            for (Reminder r : reminders) {
                LocalDateTime reminderTime =
                        r.getAppointmentDateTime()
                         .minusMinutes(r.getReminderOffsetMinutes());

                if (now.isAfter(reminderTime) && r.getLastSentAt() == null) {
System.out.println(
    "🔔 Reminder: " + r.getAppointmentTitle() +
    " at " + r.getAppointmentDateTime()
);
                    dao.markSent(r.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
