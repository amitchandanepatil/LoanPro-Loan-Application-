package com.axis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

public class ProfileUpdater {
	    public void updateProfile(Scanner scanner) {
	        System.out.println("What do you want to update?");
	        System.out.println("1. Password");
	        System.out.println("2. Date of Birth");
	        System.out.println("3. Address");
	        System.out.println("4. Contact");
	        
	        int choice = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character
	        
	        switch (choice) {
	            
	            case 1:
	                updatePassword(scanner);
	                break;
	            case 2:
	            	updateDateOfBirth(scanner);
	            	break;
	            case 3:
	            	updateAddress(scanner);
	            	break;
	            case 4:
	            	updateContact(scanner);
	            	break;
	            	
	            	
	            // Add cases for other fields as needed
	            default:
	                System.out.println("Invalid choice.");
	        }
	    }
	    
	    private void updateName(Scanner scanner) {
	        System.out.println("Enter your new Name:");
	        String newName = scanner.nextLine();
	        String loggedInUsername = LogReg.getLoggedInUsername();
	        
	        try {
	            Connection con = DBFunction.connectDB("mysql", "root", "root");
	            if (con != null) {
	                String updateQuery = "UPDATE user_tbl SET userName=? WHERE userName=?";
	                PreparedStatement pstmt = con.prepareStatement(updateQuery);
	                pstmt.setString(1, newName);
	                pstmt.setString(2, loggedInUsername);

	                int updateStatus = pstmt.executeUpdate();

	                if (updateStatus > 0) {
	                    System.out.println("Name updated successfully.");
	                } else {
	                    System.out.println("Error updating name.");
	                }

	                con.close();
	            } else {
	                System.out.println("Failed to connect to the database.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }
	    
	    private void updatePassword(Scanner scanner) {
	        System.out.println("Enter your new Password:");
	        String newPassword = scanner.next();
	        System.out.println("Confirm your Password:");
	        String confirmPassword = scanner.next();

	        if (!newPassword.equals(confirmPassword)) {
	            System.out.println("Passwords do not match.");
	            return;
	        }

	        String hashedPassword = DigestUtils.sha256Hex(newPassword);
	        String loggedInUsername = LogReg.getLoggedInUsername();

	        try {
	            Connection con = DBFunction.connectDB("mysql", "root", "root");
	            if (con != null) {
	                String updateQuery = "UPDATE user_tbl SET password=? WHERE userName=?";
	                PreparedStatement pstmt = con.prepareStatement(updateQuery);
	                pstmt.setString(1, hashedPassword);
	                pstmt.setString(2, loggedInUsername);

	                int updateStatus = pstmt.executeUpdate();

	                if (updateStatus > 0) {
	                    System.out.println("Password updated successfully.");
	                } else {
	                    System.out.println("Error updating password.");
	                }

	                con.close();
	            } else {
	                System.out.println("Failed to connect to the database.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }

	    private void updateDateOfBirth(Scanner scanner) {
	        System.out.println("Enter your new Date of Birth (YYYY-MM-DD):");
	        String newDateOfBirth = scanner.next();
	        
	        // Validate the format of the date
	        if (!isValidDateFormat(newDateOfBirth)) {
	            System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
	            return;
	        }

	        String loggedInUsername = LogReg.getLoggedInUsername();

	        try {
	            Connection con = DBFunction.connectDB("mysql", "root", "root");
	            if (con != null) {
	                String updateQuery = "UPDATE user_tbl SET dob=? WHERE userName=?";
	                PreparedStatement pstmt = con.prepareStatement(updateQuery);
	                pstmt.setString(1, newDateOfBirth);
	                pstmt.setString(2, loggedInUsername);

	                int updateStatus = pstmt.executeUpdate();

	                if (updateStatus > 0) {
	                    System.out.println("Date of Birth updated successfully.");
	                } else {
	                    System.out.println("Error updating Date of Birth.");
	                }

	                con.close();
	            } else {
	                System.out.println("Failed to connect to the database.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }

	    // Method to validate the format of the date
	    private boolean isValidDateFormat(String date) {
	        String regex = "\\d{4}-\\d{2}-\\d{2}";
	        return date.matches(regex);
	    }
	    
	    private void updateAddress(Scanner scanner) {
	        System.out.println("Enter your new Address:");
	        String newAddress = scanner.nextLine();
	        String loggedInUsername = LogReg.getLoggedInUsername();

	        try {
	            Connection con = DBFunction.connectDB("mysql", "root", "root");
	            if (con != null) {
	                String updateQuery = "UPDATE user_tbl SET address=? WHERE userName=?";
	                PreparedStatement pstmt = con.prepareStatement(updateQuery);
	                pstmt.setString(1, newAddress);
	                pstmt.setString(2, loggedInUsername);

	                int updateStatus = pstmt.executeUpdate();

	                if (updateStatus > 0) {
	                    System.out.println("Address updated successfully.");
	                } else {
	                    System.out.println("Error updating Address.");
	                }

	                con.close();
	            } else {
	                System.out.println("Failed to connect to the database.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }

	    private void updateContact(Scanner scanner) {
	        System.out.println("Enter your new Contact:");
	        String newContact = scanner.nextLine();
	        String loggedInUsername = LogReg.getLoggedInUsername();

	        try {
	            Connection con = DBFunction.connectDB("mysql", "root", "root");
	            if (con != null) {
	                String updateQuery = "UPDATE user_tbl SET contact=? WHERE userName=?";
	                PreparedStatement pstmt = con.prepareStatement(updateQuery);
	                pstmt.setString(1, newContact);
	                pstmt.setString(2, loggedInUsername);

	                int updateStatus = pstmt.executeUpdate();

	                if (updateStatus > 0) {
	                    System.out.println("Contact updated successfully.");
	                } else {
	                    System.out.println("Error updating Contact.");
	                }

	                con.close();
	            } else {
	                System.out.println("Failed to connect to the database.");
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	        }
	    }


	    
	    // Add similar methods for updating other fields like date of birth, address, contact, PAN, etc.
	}



