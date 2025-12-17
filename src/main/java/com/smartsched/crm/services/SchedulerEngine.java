package com.smartsched.crm.services;

import java.util.concurrent.*;
import com.smartsched.crm.dao.ReminderDAO;

public class SchedulerEngine {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ReminderService reminderService = new ReminderService();

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                reminderService.scanAndSendReminders();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
