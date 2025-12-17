package com.smartsched.crm.ui;

import com.smartsched.crm.dao.AppointmentDAO;
import com.smartsched.crm.dao.ClientDAO;
import com.smartsched.crm.models.Client;
import java.util.*;

public class ClientUI {

    private Scanner sc = new Scanner(System.in);
    private ClientDAO dao = new ClientDAO();

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Client Management ===");
            System.out.println("1. Add Client");
            System.out.println("2. View Clients");
            System.out.println("3. Update Client");
            System.out.println("4. Delete Client");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");

            int ch = Integer.parseInt(sc.nextLine());

            try {
                switch (ch) {
                    case 1 -> addClient();
                    case 2 -> viewClients();
                    case 3 -> updateClient();
                    case 4 -> deleteClient();
                    case 5 -> { return; }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addClient() throws Exception {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        Client c = new Client(name, phone, email, "", "");
        dao.addClient(c);

        System.out.println("✅ Client added");
    }

    private void viewClients() throws Exception {
        List<Client> list = dao.getAllClients();

        System.out.println("\n--- Clients ---");
        for (Client c : list) {
            System.out.println(
                c.getId() + " | " +
                c.getName() + " | " +
                c.getPhone() + " | " +
                c.getEmail()
            );
        }
        System.out.println("----------------");
    }

    private void updateClient() throws Exception {
        System.out.print("Enter Client ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Phone: ");
        String phone = sc.nextLine();
        System.out.print("New Email: ");
        String email = sc.nextLine();

        Client c = new Client(name, phone, email, "", "");
        c.setId(id);

        dao.updateClient(c);
        System.out.println("✅ Client updated");
    }

    private void deleteClient() throws Exception {
        System.out.print("Enter Client ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        // Step 1: Block if active appointments exist
if (dao.hasActiveAppointments(id)) {
    System.out.println("❌ Cannot delete client. Active appointments exist.");
    System.out.println("👉 Cancel appointments first.");
    return;
}

// Step 2: Remove cancelled appointments
AppointmentDAO appointmentDAO = new AppointmentDAO();
appointmentDAO.deleteCancelledAppointmentsByClient(id);

// Step 3: Delete client
dao.deleteClient(id);
System.out.println("🗑 Client deleted successfully");


dao.deleteClient(id);
System.out.println("🗑 Client deleted");


    }
}
