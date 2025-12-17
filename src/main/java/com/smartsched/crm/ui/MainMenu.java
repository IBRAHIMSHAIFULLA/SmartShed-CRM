package com.smartsched.crm.ui;

import java.util.Scanner;
import com.smartsched.crm.services.SchedulerEngine;

public class MainMenu {

    private Scanner sc = new Scanner(System.in);
    private SchedulerEngine scheduler = new SchedulerEngine();

    public void start() {
        scheduler.start();
        boolean running = true;

        while (running) {
            System.out.println("\n=== SmartSched CRM ===");
            System.out.println("1. Client Management");
            System.out.println("2. Appointment Management");
            System.out.println("3. View Dashboard");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1 -> new ClientUI().showMenu();
                case 2 -> new AppointmentUI().showMenu();
                case 3 -> new DashboardUI().show();
                case 4 -> {
                    running = false;
                    scheduler.stop();
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
