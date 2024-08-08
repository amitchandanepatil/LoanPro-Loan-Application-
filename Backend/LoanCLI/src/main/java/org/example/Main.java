package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/loanpro", "root", "Amit@8055");
            System.out.println("Connected to database!");
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }
}