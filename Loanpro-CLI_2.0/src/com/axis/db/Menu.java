package com.axis.db;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		applyLoan(scan);
		
		Connection conn = DBFunction.connectDB("mysql", null, null);
		
		
    }
	
		//User - payEMI
	public void payEMI(String loggedInUsername, Scanner scanner) {
	    try {
	        Connection con = DBFunction.connectDB("mysql", "root", "root");
	        if (con != null) {
	            
	            String loanAppIdQuery = "SELECT id FROM loan_application WHERE user_id=(SELECT id FROM user_tbl WHERE userName=?)";
	            PreparedStatement loanAppIdPst = con.prepareStatement(loanAppIdQuery);
	            loanAppIdPst.setString(1, loggedInUsername);
	            ResultSet loanAppIdRs = loanAppIdPst.executeQuery();

	            if (loanAppIdRs.next()) {
	                int loanAppId = loanAppIdRs.getInt("id");
	                displayLoan(loggedInUsername);
	                System.out.println("Enter your load ID for which you want to pay EMI:");
	                Scanner sc= new Scanner(System.in);
	                int loanID = sc.nextInt();
	                System.out.println("Loan Application ID for " + loggedInUsername + ": " + loanID);

	                //Check EMI Status
	                String checkEMIquery = "SELECT loan_amount ,remaining_amount from loan_application WHERE id=?";
	                PreparedStatement checkEMIpst = con.prepareStatement(checkEMIquery);
	                PreparedStatement selectLoanAmPst = con.prepareStatement(checkEMIquery);
	                checkEMIpst.setInt(1,loanID);
	                ResultSet checkEMIRs = checkEMIpst.executeQuery();
	                if (checkEMIRs.next()) {
	                    double currentLoanAmount1 = checkEMIRs.getDouble("loan_amount");
	                    double remainingAmount1 = checkEMIRs.getDouble("remaining_amount");
	                    System.out.println(currentLoanAmount1);
	                    System.out.println(remainingAmount1);

	                    if (remainingAmount1 == 0) {
	                    	System.out.println("LOAN EMI PAYMENT COMPLETED !!!");
	                    }
	                
	                    else {
	                
	                System.out.println("Enter the EMI amount you want to pay:");
	                float emiAmount = scanner.nextFloat();

	                
	                String loanAmountQuery = "SELECT loan_amount,remaining_amount FROM loan_application WHERE id=?";
	                PreparedStatement loanAmountPst = con.prepareStatement(loanAmountQuery);
	                loanAmountPst.setInt(1, loanID);
	                ResultSet loanAmountRs = loanAmountPst.executeQuery();

	                if (loanAmountRs.next()) {
	                    double remainingLoanAmount = loanAmountRs.getDouble("remaining_amount");
	                     remainingLoanAmount = remainingLoanAmount - emiAmount;
	                    if (remainingLoanAmount >= 0) {
	                        System.out.println("EMI Paid: " + emiAmount);
	                        System.out.println("Remaining Loan Amount: " + remainingLoanAmount);
                            
	                        
	                        String updateLoanAmountQuery = "UPDATE loan_application SET remaining_amount=? WHERE id=?";
	                        PreparedStatement updateLoanAmountPst = con.prepareStatement(updateLoanAmountQuery);
	                        updateLoanAmountPst.setDouble(1, remainingLoanAmount);
	                        updateLoanAmountPst.setInt(2, loanID);
	                        updateLoanAmountPst.executeUpdate();

	                        
	                        String repaymentExistQuery = "SELECT id FROM repayment_schedule WHERE loan_application_id=?";
	                        PreparedStatement repaymentExistPst = con.prepareStatement(repaymentExistQuery);
	                        repaymentExistPst.setInt(1, loanID);
	                        ResultSet repaymentExistRs = repaymentExistPst.executeQuery();

	                        if (repaymentExistRs.next()) {
	                            
	                            String updateRepaymentQuery = "UPDATE repayment_schedule SET amount=? WHERE loan_application_id=?";
	                            PreparedStatement updateRepaymentPst = con.prepareStatement(updateRepaymentQuery);
	                            updateRepaymentPst.setFloat(1, (float) remainingLoanAmount); 
	                            updateRepaymentPst.setInt(2, loanID);
	                            updateRepaymentPst.executeUpdate();

	                            System.out.println("Repayment schedule updated successfully.");
	                        } else {
	                            
	                            String insertRepaymentQuery = "INSERT INTO repayment_schedule (loan_application_id, amount) VALUES (?, ?)";
	                            PreparedStatement insertRepaymentPst = con.prepareStatement(insertRepaymentQuery);
	                            insertRepaymentPst.setInt(1, loanID);
	                            insertRepaymentPst.setFloat(2, (float) remainingLoanAmount); 
	                            insertRepaymentPst.executeUpdate();

	                            System.out.println("New entry added to the repayment schedule.");
	                        }

	                        System.out.println("Loan amount and repayment schedule updated successfully.");
	          
	                    } else {
	                        System.out.println("Invalid EMI amount. Please enter a valid amount.");
	                    }
	                } else {
	                    System.out.println("Failed to fetch loan amount.");
	                }
	            }} else {
	                System.out.println("No Loan Application ID found for " + loggedInUsername);
	            }

	            con.close();
	        } else {
	            System.out.println("Failed to connect to the database.");
	     
	        }
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("SQL Exception: " + e.getMessage());
	    }
	     
	}
	


	
	//User Update Profile
    public void updateProfile(Scanner scanner) {
        System.out.println("Enter your new Username=");
        String username = scanner.next();
        System.out.println("Enter your new Password=");
        String password = scanner.next();
        System.out.println("Confirm your Password=");
        String password2 = scanner.next();

        if (password.equals(password2)) {
            try {
                Connection con = DBFunction.connectDB("mysql", "root", "root");
                if (con != null) {
                    String updateQuery = "UPDATE user_tbl SET userName=?, password=? WHERE userName=?";
                    PreparedStatement pstmt = con.prepareStatement(updateQuery);

                    pstmt.setString(1, username);
                    pstmt.setString(2, password);
                    pstmt.setString(3, LogReg.getLoggedInUsername()); // Get the logged-in username

                    int updateStatus = pstmt.executeUpdate();

                    if (updateStatus > 0) {
                        System.out.println("Profile Updated Successfully");
                    } else {
                        System.out.println("Error updating profile.");
                    }

                    con.close();
                } else {
                    System.out.println("Failed to connect to the database.");
                }
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
            }
        } else {
            System.out.println("Passwords do not match.");
        }
    }
		
		//User- Display Existing Loan Details
		public static void displayLoan(String username) {
			try {
		        Connection con = DBFunction.connectDB("mysql", "root", "root");
		        if (con != null) {
		        	String selectQuery = "SELECT l.id, l.income, l.loan_amount, l.loan_type, l.loan_terms ,l.employment_status,l.timestamp,l.remaining_amount, u.userName " +
		                     "FROM loan_application l " +
		                     "JOIN user_tbl u ON l.user_id = u.id " +  // Added space before WHERE
		                     "WHERE u.userName=?";
		            PreparedStatement pstmt = con.prepareStatement(selectQuery);
		            pstmt.setString(1, username);
		            ResultSet rs = pstmt.executeQuery();

		            System.out.println("Loan Details:");
		            System.out.println("ID\tIncome\tLoan Amount\tLoan Type\tUser Name\t Loan Terms\t Employment Status\t Remaining Amount \t Created On");

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

		                System.out.println(id + "\t" + income + "\t" + loanAmount + "\t\t" + loanType + "\t" + userName + "\t\t\t" + loan_terms + "\t\t\t" + employment_status +"\t" +remaining_amount + "\t\t"+ timestamp);
		                
		            }

		            con.close();
		        } else {
		            System.out.println("Failed to connect to the database.");
		        }
		    } catch (SQLException e) {
		        System.out.println("SQL Exception: " + e.getMessage());
		    }
	    }
		
		// User- Applying Loan
		public static void applyLoan(Scanner scanner) {
		    double income, loan_amount;
		    int loan_type,loan_terms,employment_status;
		    System.out.println("\nEnter your Income: ");
		    income = scanner.nextDouble();

		    System.out.println("\nEnter Loan Amount: ");
		    loan_amount = scanner.nextDouble();

		    System.out.println("\nEnter Loan Type: ");
		    System.out.println("\t\t\t 1. Personal Loan ");
		    System.out.println("\t\t\t 2. Home Loan ");
		    System.out.println("\t\t\t 3. Business Loan ");
		    System.out.println("\t\t\t 4. Education Loan");
		    loan_type = scanner.nextInt();
		    
		    System.out.println("\nEnter Loan Period(in months): ");
		    loan_terms = scanner.nextInt();
		    
		    System.out.println("\nEnter Employment Status: ");
		    System.out.println("\t\t\t 1. Single ");
		    System.out.println("\t\t\t 2. Married ");
		    System.out.println("\t\t\t 3. Divorced ");
		    System.out.println("\t\t\t 4. Widowed");
		    employment_status = scanner.nextInt();

		    if ((loan_type >= 1 && loan_type <= 4) && (employment_status >=1 && employment_status <=4) ) {
		        try {
		            String loggedInUsername = LogReg.getLoggedInUsername(); // Get the logged-in username
		            Connection con = DBFunction.connectDB("mysql", "root", "root");
		            if (con != null) {
		                // Retrieve user_id based on the logged-in username
		                String selectQuery = "SELECT id FROM user_tbl WHERE userName=?";
		                PreparedStatement pstmtSelect = con.prepareStatement(selectQuery);
		                pstmtSelect.setString(1, loggedInUsername);
		                ResultSet rs = pstmtSelect.executeQuery();

		                if (rs.next()) {
		                    int user_id = rs.getInt("id");
		                    String insertQuery = "INSERT INTO loan_application (income, loan_amount, loan_type, user_id,loan_terms,employment_status) VALUES (?, ?, ?, ?,?,?)";
		                    PreparedStatement pstmt = con.prepareStatement(insertQuery);
		                    pstmt.setDouble(1, income);
		                    pstmt.setDouble(2, loan_amount);
		                    pstmt.setInt(3, loan_type);
		                    pstmt.setInt(4, user_id);
		                    pstmt.setInt(5, loan_terms);
		                    pstmt.setInt(6, employment_status);
		                    int insertStatus = pstmt.executeUpdate();
		                    
                            String insertQuery1 = "INSERT INTO loan_application remaining_amount VALUES '"+loan_amount+"' ";
                            PreparedStatement pstmt1 = con.prepareStatement(insertQuery);
                            int insertStatus1 = pstmt1.executeUpdate();
                            
		                    if (insertStatus > 0) {
		                        System.out.println(insertStatus + " Record Inserted Successfully");
		                        if (income > 50000 && loan_amount > 50000) {
		                            System.out.println("Loan Approved!!!!!");
		                            LogReg.displayMenu();
		                        } else {
		                            System.out.println("Loan Not Approved!!!!!");
		                        }
		                    } else {
		                        System.out.println("Error inserting record.");
		                    }
		                } else {
		                    System.out.println("User not found.");
		                }
		                con.close();
		            } else {
		                System.out.println("Failed to connect to the database.");
		            }
		        } catch (SQLException e) {
		            System.out.println("SQL Exception: " + e.getMessage());
		        }
		    } else {
		        System.out.println("Invalid Loan Type.");
		    }
		}
	        	
	        }
	        

	        

	        



