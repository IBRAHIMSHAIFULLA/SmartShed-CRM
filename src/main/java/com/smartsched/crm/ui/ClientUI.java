package com.smartsched.crm.ui;

import com.smartsched.crm.dao.ClientDAO;
import com.smartsched.crm.models.Client;
import java.util.*;

public class ClientUI {
    private Scanner sc = new Scanner(System.in);
    private ClientDAO dao = new ClientDAO();

    public void showMenu() {
        while (true) {
            System.out.println("1. Add Client");
            System.out.println("2. View Clients");
            System.out.println("3. Back");

            int ch = Integer.parseInt(sc.nextLine());
            try {
                if (ch == 1) addClient();
                else if (ch == 2) viewClients();
                else return;
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
        for (Client c : dao.getAllClients()) {
            System.out.println(c.getId() + " | " + c.getName() + " | " + c.getPhone());
        }
    }
}
