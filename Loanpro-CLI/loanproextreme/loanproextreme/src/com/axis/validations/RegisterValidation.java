package com.axis.validations;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterValidation {

	




    // Your existing code here...

    public static void registerVal()  {
        

        

        String email=null;
		
		// Validate email format
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please enter a valid email.");
            registerVal(); // Recursively call register method to retry input
            return; // Exit the current method to avoid further processing
        }

        String contact=null;
		// Validate contact number format
        if (!isValidContact(contact)) {
            System.out.println("Invalid contact number format. Please enter a valid number.");
            registerVal(); // Recursively call register method to retry input
            return; // Exit the current method to avoid further processing
        }

        // Your existing code here...
    }

    // Method to validate email format
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Method to validate contact number format
    private static boolean isValidContact(String contact) {
        // Assuming a 10-digit contact number for simplicity
        String contactRegex = "\\d{10}";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
}

