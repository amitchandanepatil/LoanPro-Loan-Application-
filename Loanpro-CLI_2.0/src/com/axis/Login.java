package com.axis;

import java.util.Scanner;

public class Login {

    public static void main(String[] args) {
        String username = null;
        String password = null;
        Scanner scan = new Scanner(System.in);
        displayStyle();
        System.out.println("\n**********************       Welcome to Insta Loan        **********************");
        displayStyle();
        System.out.print("\n Enter User Name (email/mobile) :");
        username = scan.next();
        System.out.print("\n Enter your Password :");
        password = scan.next();

        if (username != null && password != null) {
            if (username.equals("user") && password.equals("test")) {
                System.out.println("Welcome " + username + " to the LoanPro Application");
               while(true) {
            	   
               displayMenu();
            	
               }
                
            } else {
                System.out.println("Invalid Credentials!!! Pls Try Again!!!!");
            }
        }
        scan.close();
    }

    public static void displayStyle() {
        for (int i = 0; i < 80; i++) {
            System.out.print("*");
        }
    }
  
    public static void displayMenu() {
        System.out.println("\t\t\t 1. Display Existing Loan details ");
        System.out.println("\t\t\t 2. Apply for a new Loan ");
        System.out.println("\t\t\t 3. Pay EMI for the Loan ");
        System.out.println("\t\t\t 4. Update Profile details");
        System.out.println("\t\t\t 5. Exit ");
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Enter your Choice=");
        choice = scan.nextInt();

        Login.MenuChoice(choice);
       
    }

    public static void MenuChoice(int choice) {
        int age, period;
        double income, emi, loan_amount, res;
        String username = null;
        String password = null;
        String password2 = null;

        Scanner scan = new Scanner(System.in);

        switch (choice) {
            case 1:
                System.out.println("Displaying Existing Loan details...");
                System.out.println("  Username \t\t  Password");
                System.out.println(" x  \t\t  Pass123");
                System.out.println(" y  \t\t  Pass321");
                break;
            case 2:
                System.out.println("Applying for a new Loan...");
                System.out.println("Enter your Income=");
                income = scan.nextDouble();
                System.out.println("Enter your Age=");
                age = scan.nextInt();
                System.out.println("Enter Period (in months)=");
                period = scan.nextInt();

                if (income > 50000 && age > 18 && period > 6) {
                    System.out.println("Loan Approved!!!!!");
                } else {
                    System.out.println("Loan Not Approved!!!!!");
                }
                break;
            case 3:
                System.out.println("Paying EMI for the Loan...");
                System.out.println("Enter your Loan Amount=");
                loan_amount = scan.nextDouble();
                System.out.println("Enter your EMI Amount=");
                emi = scan.nextDouble();
                res = loan_amount - emi;
                System.out.println("Loan Paid!!!!");
                System.out.println("EMI Paid!!!!");
                System.out.println("Your Remaining EMI Amount = " + res);
                break;
            case 4:
                System.out.println("Updating Profile details...");
                System.out.println("Enter your new Username=");
                username = scan.next();
                System.out.println("Enter your new Password=");
                password = scan.next();
                System.out.println("Confirm your Password=");
                password2 = scan.next();

                if (password.equals(password2)) {
                    System.out.println("****Profile Updated****");
                } else {
                    System.out.println("****Password Not Matched! Retry!!****");
                }
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0); // Exit the application
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }
}
