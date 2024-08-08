package com.axis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Admin_Menu {
	
	// view All loan
	public static void viewLoan() {
		
		try {
	        Connection con = DBFunction.connectDB("mysql", "root", "root");
	        if (con != null) {
	        	String selectQuery = "SELECT l.id, l.income, l.loan_amount, l.loan_type, l.loan_terms ,l.employment_status,l.timestamp,l.remaining_amount, u.userName " +
	                     "FROM loan_application l " +
	                     "JOIN user_tbl u ON l.user_id = u.id "   // Added space before WHERE
	                   ;
	            PreparedStatement pstmt = con.prepareStatement(selectQuery);
	            ResultSet rs = pstmt.executeQuery();

	            System.out.println("Loan Details:");
	            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");
	            System.out.println("| ID |   Income   |  Loan Amount | Loan Type| User Name  | Loan Terms | Employment Status | Remaining Amt  |     Created On      |");
	            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                double income = rs.getDouble("income");
	                double loanAmount = rs.getDouble("loan_amount");
	                int loanType = rs.getInt("loan_type");
	                String userName = rs.getString("userName");
	                int loan_terms = rs.getInt("loan_terms");
	                int remaining_amount = rs.getInt("remaining_amount");
	                int employment_status = rs.getInt("employment_status");
	                java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                String formattedTimestamp = dateFormat.format(timestamp);

	                // Format the output in a table-like view
	                System.out.printf("| %-2d | %9.2f | %11.2f |    %-6d | %-10s | %-10d |        %-8d    | %12d | %-20s |%n",
	                        id, income, loanAmount, loanType, userName, loan_terms, employment_status, remaining_amount, formattedTimestamp);
	            }

	            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");


	            con.close();
	        } else {
	            System.out.println("Failed to connect to the database.");
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Exception: " + e.getMessage());
	    }
    }
	


	public void updateStatus()
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("1. view submitted Application: ");
		System.out.println("2. view Approved loan Application: ");
		int ch=sc.nextInt();
		
		
		if(ch == 1)
		{
			try {
		        Connection con = DBFunction.connectDB("mysql", "root", "root");
		        if (con != null) {
		        	int lst=0;
		        	String selectQuery = "SELECT l.id, l.income, l.loan_amount, l.loan_type, l.loan_terms ,l.employment_status,l.timestamp,l.remaining_amount, u.userName " +
		                     "FROM loan_application l " +
		                     "JOIN user_tbl u ON l.user_id = u.id " + "WHERE l.loan_status=?"   // Added space before WHERE
		                   ;
		            PreparedStatement pstmt = con.prepareStatement(selectQuery);
		            pstmt.setInt(1, lst);
		            ResultSet rs = pstmt.executeQuery();
		            System.out.println("Loan Details:");
		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");
		            System.out.println("| ID |   Income   |  Loan Amount | Loan Type| User Name  | Loan Terms | Employment Status | Remaining Amt  |     Created On      |");
		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");

		            while (rs.next()) {
		                int id = rs.getInt("id");
		                double income = rs.getDouble("income");
		                double loanAmount = rs.getDouble("loan_amount");
		                int loanType = rs.getInt("loan_type");
		                String userName = rs.getString("userName");
		                int loan_terms = rs.getInt("loan_terms");
		                int remaining_amount = rs.getInt("remaining_amount");
		                int employment_status = rs.getInt("employment_status");
		                java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                String formattedTimestamp = dateFormat.format(timestamp);

		                // Format the output in a table-like view
		                System.out.printf("| %-2d | %9.2f | %11.2f |    %-6d | %-10s | %-10d |        %-8d    | %12d | %-20s |%n",
		                        id, income, loanAmount, loanType, userName, loan_terms, employment_status, remaining_amount, formattedTimestamp);
		            }

		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");

		            
		            

		            con.close();
		        } else {
		            System.out.println("Failed to connect to the database.");
		        }
		    } catch (SQLException e) {
		        System.out.println("SQL Exception: " + e.getMessage());
		    }
			
			//Approved status 

			System.out.println("1.Approve User Loan Application: ");
			System.out.println("2.Reject User Loan Application: ");
			System.out.println("3.Exit");
			int us=sc.nextInt();
			
			if(us==1)
			{
				System.out.println("Enter loan id:");
				int lid=sc.nextInt();
				
				if(loanIdExist(lid))
				{
				
				int lst=1;
				
				String sql="update loan_application set loan_status='"+lst+"' where id='"+lid+"' ";
				try
				{
					Connection con = DBFunction.connectDB("mysql", "root", "root");
			        if (con != null) {
					Statement st =con.createStatement();
					int i=st.executeUpdate(sql);
					
					if(i>0)
					{
						System.out.println("loan approved sucesfully");
					}
					else
					{
						System.out.println("failed to update");
					}
			        }
					
				}catch(Exception e) {}
				}
				else
				{
					System.out.println("Id does not exist");
				}
			}
			else if(us==2)
			{
				System.out.println("Enter loan id:");
				int lid=sc.nextInt();
				
				if(loanIdExist(lid))
				{
				
				int lst=2;
				
				String sql="update loan_application set loan_status='"+lst+"' where id='"+lid+"' ";
				try
				{
					Connection con = DBFunction.connectDB("mysql", "root", "root");
			        if (con != null) {
					Statement st =con.createStatement();
					int i=st.executeUpdate(sql);
					
					if(i>0)
					{
						System.out.println("loan approved sucesfully");
					}
					else
					{
						System.out.println("failed to update");
					}
			        }
					
				}catch(Exception e) {}
				}
				else
				{
					System.out.println("Id does not exist");
				}
			}
			else
			{
				System.out.print("Exiting ");
        		int i=5;
        		
        		while(i!=0)
        		{
        			System.out.print(".");
        			try {
        				Thread.sleep(500);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			i--;
        			
        		}
        		
        		System.out.println("Exited Sucessfully");
        		System.exit(0);
                
			}
		}
		
		//Approved
		else if( ch == 2)
		{
			try {
		        Connection con = DBFunction.connectDB("mysql", "root", "root");
		        if (con != null) {
		        	int lst=1;
		        	String selectQuery = "SELECT l.id, l.income, l.loan_amount, l.loan_type, l.loan_terms ,l.employment_status,l.timestamp,l.remaining_amount, u.userName " +
		                     "FROM loan_application l " +
		                     "JOIN user_tbl u ON l.user_id = u.id " + "WHERE l.loan_status=?"   // Added space before WHERE
		                   ;
		            PreparedStatement pstmt = con.prepareStatement(selectQuery);
		            pstmt.setInt(1, lst);
		            ResultSet rs = pstmt.executeQuery();

		            System.out.println("Loan Details:");
		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");
		            System.out.println("| ID |   Income   |  Loan Amount | Loan Type| User Name  | Loan Terms | Employment Status | Remaining Amt  |     Created On      |");
		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");

		            while (rs.next()) {
		                int id = rs.getInt("id");
		                double income = rs.getDouble("income");
		                double loanAmount = rs.getDouble("loan_amount");
		                int loanType = rs.getInt("loan_type");
		                String userName = rs.getString("userName");
		                int loan_terms = rs.getInt("loan_terms");
		                int remaining_amount = rs.getInt("remaining_amount");
		                int employment_status = rs.getInt("employment_status");
		                java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");
		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                String formattedTimestamp = dateFormat.format(timestamp);

		                // Format the output in a table-like view
		                System.out.printf("| %-2d | %9.2f | %11.2f |    %-6d | %-10s | %-10d |        %-8d    | %12d | %-20s |%n",
		                        id, income, loanAmount, loanType, userName, loan_terms, employment_status, remaining_amount, formattedTimestamp);
		            }

		            System.out.println("+----+------------+--------------+----------+------------+------------+------------------+----------------+----------------------+");


		            con.close();
		        } else {
		            System.out.println("Failed to connect to the database.");
		        }
		    } catch (SQLException e) {
		        System.out.println("SQL Exception: " + e.getMessage());
		    }
		}
		else
		{
			System.out.println("Invalid Input");
		}
		
		
			
	
	}	
	
	public static boolean loanIdExist(int lid)
	{
		String sql=" select id from loan_application where id = '"+lid+"' ";
		try
		{
		Connection con = DBFunction.connectDB("mysql", "root", "root");
		Statement st=con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		return rs.next();
		
		
		}catch(Exception e) {}
		return false;
		
	}
	
}	

