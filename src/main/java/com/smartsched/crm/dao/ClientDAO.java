package com.smartsched.crm.dao;

import com.smartsched.crm.Database;
import com.smartsched.crm.models.Client;
import java.sql.*;
import java.util.*;

public class ClientDAO {

    public void addClient(Client c) throws SQLException {
        String sql = "INSERT INTO clients(name, phone, email, address, notes) VALUES (?,?,?,?,?)";
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getNotes());
            ps.executeUpdate();
        }
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> list = new ArrayList<>();
        String sql = "SELECT * FROM clients";

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Client c = new Client(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("notes")
                );
                c.setId(rs.getInt("id"));
                list.add(c);
            }
        }
        return list;
    }
    // Update client
public void updateClient(Client c) throws SQLException {
    String sql = """
        UPDATE clients
        SET name = ?, phone = ?, email = ?, address = ?, notes = ?
        WHERE id = ?
    """;

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, c.getName());
        ps.setString(2, c.getPhone());
        ps.setString(3, c.getEmail());
        ps.setString(4, c.getAddress());
        ps.setString(5, c.getNotes());
        ps.setInt(6, c.getId());

        ps.executeUpdate();
    }
}

// Delete client
public void deleteClient(int clientId) throws SQLException {
    String sql = "DELETE FROM clients WHERE id = ?";

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, clientId);
        ps.executeUpdate();
    }
}
public boolean hasAppointments(int clientId) throws SQLException {
    String sql = "SELECT COUNT(*) FROM appointments WHERE client_id = ?";

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, clientId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }
}
public boolean hasActiveAppointments(int clientId) throws SQLException {
    String sql = """
        SELECT COUNT(*)
        FROM appointments
        WHERE client_id = ? AND status != 'CANCELLED'
    """;

    try (Connection con = Database.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, clientId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }
}

}
