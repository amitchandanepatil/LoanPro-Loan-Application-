package com.axis.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class LoanApplication {
	private static LogReg obj = new LogReg();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
    	System.out.print("\n Welcome !! Select one option..");
        System.out.println("\n 1.Register: ");
        System.out.println("\n 2.Login");
        int ch = scanner.nextInt();
        scanner.nextLine(); 
        LogReg obj=new LogReg();
        if (ch == 1) {
            obj.register(scanner);
            
            
        } else if (ch == 2) {
            obj.login(scanner);
        } else {
            System.out.println("Invalid choice.");
        }
        
       
    }
    

  
}