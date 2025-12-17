package com.smartsched.crm.ui;

import com.smartsched.crm.dao.AppointmentDAO;
import com.smartsched.crm.models.Appointment;
import java.time.*;
import java.util.*;

public class AppointmentUI {

    private Scanner sc = new Scanner(System.in);
    private AppointmentDAO dao = new AppointmentDAO();

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Appointment Management ===");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            switch (ch) {
                case 1 -> addAppointment();
                case 2 -> viewAppointments();
                case 3 -> cancelAppointment();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice!");
            }

        }
    }

    private void addAppointment() {
        try {
            Appointment a = new Appointment();

            System.out.print("Client ID: ");
            a.setClientId(Integer.parseInt(sc.nextLine()));

            System.out.print("Title: ");
            a.setTitle(sc.nextLine());

            System.out.print("Description: ");
            a.setDescription(sc.nextLine());

            System.out.print("Date (YYYY-MM-DD): ");
            a.setAppointmentDate(LocalDate.parse(sc.nextLine()));

            System.out.print("Time (HH:MM): ");
            a.setAppointmentTime(LocalTime.parse(sc.nextLine()));

            a.setPriority("MEDIUM");

            dao.addAppointment(a);
            System.out.println("✅ Appointment scheduled");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void viewAppointments() {
        try {
            List<Appointment> list = dao.getAllAppointments();

            if (list.isEmpty()) {
                System.out.println("No appointments found.");
                return;
            }

            System.out.println("\n--- Appointments List ---");
            for (Appointment a : list) {
                System.out.println(
                    "ID: " + a.getId() +
                    " | Client: " + a.getClientId() +
                    " | " + a.getTitle() +
                    " | " + a.getAppointmentDate() +
                    " " + a.getAppointmentTime() +
                    " | " + a.getStatus()
                );
            }
            System.out.println("--------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void cancelAppointment() {
    try {
        System.out.print("Enter Appointment ID to cancel: ");
        int id = Integer.parseInt(sc.nextLine());

        dao.cancelAppointment(id);
        System.out.println("❌ Appointment cancelled");

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
