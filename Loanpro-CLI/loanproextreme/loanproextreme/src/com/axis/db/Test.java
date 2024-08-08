package com.axis.db;

import java.sql.*;

public class Test {
    public static void takeId(String loggedInUsername) {
        // Get the userName from the user
        String userName = loggedInUsername; // Implement this method to get input from the user
        
        // Retrieve userId from the database based on the userName
        int userId = getUserId(userName);
        
        // If userId is -1, it means userName does not exist
        if (userId == -1) {
            System.out.println("User with the specified userName does not exist.");
            return;
        }
        
        try {
            // Establish the database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/axis", "root", "root");
            
            // Create the SQL statement
            String sql = "UPDATE loan_application la " +
                         "JOIN user_tbl ut ON la.user_id = ut.id " +
                         "SET la.user_name = ? " +
                         "WHERE ut.id = ?";
            
            // Create a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setInt(2, userId);

            // Execute the SQL statement
            int rowsUpdated = pstmt.executeUpdate();
           // System.out.println("Rows updated: " + rowsUpdated);

            // Close the connection
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to retrieve userId from the database based on userName
    private static int getUserId(String userName) {
        int userId = -1;
        try {
            // Establish the database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/axis", "root", "root");
            
            // Create the SQL statement
            String sql = "SELECT id FROM user_tbl WHERE userName = ?";
            
            // Create a PreparedStatement
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userName);

            // Execute the SQL query
            ResultSet rs = pstmt.executeQuery();

            // If ResultSet is not empty, retrieve the userId
            if (rs.next()) {
                userId = rs.getInt("id");
            }

            // Close the connection
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    
}
