package org.example.DBConnection;
// Import necessary classes for database connectivity
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Define a class named DBFunction for handling database operations
public class DBFunction {
    // Declare private static variables for database connection, statement, prepared statement, and result set
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement pstatement;
    private static ResultSet resultSet;

    // Method to establish a database connection
    public static Connection connectDB(String dbName, String userName, String password) throws SQLException {
        // Initialize URL for database connection
        String url = "localhost";

        // Check if dbName is not null
        if (dbName != null) {
            // Check if dbName is "oracle"
            if (dbName.equalsIgnoreCase("oracle")) {
                // Set URL for Oracle database connection
                url = "jdbc:oracle:thin:@" + url + ":1521:xe";
                // Check if userName is null, set default username
                if (userName == null)
                    userName = "system";
                // Check if password is null, set default password
                if (password == null)
                    password = "manager";
            } else if (dbName.equalsIgnoreCase("mysql")) {
                // Set URL for MySQL database connection
                url = "jdbc:mysql://" + url + ":3306/axis";
                // Check if userName is null, set default username
                if (userName == null)
                    userName = "root";
                // Check if password is null, set default password
                if (password == null)
                    password = "Amit@8055";
            }
        } else {
            // Print error message if dbName is null
            System.out.println("DBName cannot be Empty or Null.");
        }

        // Check if userName and password are not null
        if (userName != null && password != null) {
            // Establish database connection using DriverManager
            connection = DriverManager.getConnection(url, userName, password);
        }
        // Return the database connection
        return connection;
    }

    // Method to close all database resources
    public static void closeResources() throws SQLException {
        // Check if resultSet is not null, close resultSet
        if (resultSet != null) {
            resultSet.close();
        }
        // Check if statement is not null, close statement
        if (statement != null) {
            statement.close();
        }
        // Check if pstatement is not null, close pstatement
        if (pstatement != null) {
            pstatement.close();
        }
        // Check if connection is not null, close connection
        if (connection != null) {
            connection.close();
        }
    }
}
