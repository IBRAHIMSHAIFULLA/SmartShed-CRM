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
}
