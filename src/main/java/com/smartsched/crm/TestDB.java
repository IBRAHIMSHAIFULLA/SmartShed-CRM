package com.smartsched.crm;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection con = Database.getConnection();
        if (con != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
