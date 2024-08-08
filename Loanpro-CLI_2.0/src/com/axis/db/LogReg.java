package com.axis.db;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.apache.commons.codec.digest.DigestUtils;
public class LogReg {

    private static String loggedInUsername; 

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        register(scanner);
        login(scanner);
        adminLogin(scanner);
        
        
    }
//User Display Menu
    public static void displayMenu() {
    	System.out.println("\t\t\t ");
    	System.out.println("\t\t\t ----------USER DISPLAY MENU------------");
    	System.out.println("\t\t\t --------------------------------------- ");
        System.out.println("\t\t\t|  1. Display Existing Loan details    |");
        System.out.println("\t\t\t|  2. Apply for a new Loan             |");
        System.out.println("\t\t\t|  3. Pay EMI for the Loan             |");
        System.out.println("\t\t\t|  4. Update Profile details           |");
        System.out.println("\t\t\t|  5 Loan EMI Calculator               |");
        System.out.println("\t\t\t|  6. Exit                             |");
        System.out.println("\t\t\t ----------------------------------------");
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Enter your Choice=");
        choice = scan.nextInt();

        MenuChoice(choice);
    }

    public static void MenuChoice(int choice) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        LoanCalculator lc = new LoanCalculator();

        switch (choice) {
            case 1:
                System.out.println("Displaying Existing Loan details...");
                menu.displayLoan(getLoggedInUsername());
                break;
            case 2:
                menu.applyLoan(scan);
                break;
            case 3:
                System.out.println("Paying EMI for the Loan...");
                menu.payEMI(loggedInUsername, scan);
                
                break;
            case 4:
                System.out.println("Updating Profile details...");
                menu.updateProfile(scan);
                break;
            case 5:
                System.out.println("Loan Calculator...");
                lc.loanCalculator();
                break;
            case 6:
                System.out.println("Exiting...");
                System.exit(0); // Exit the application
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }

 //Admin Display Menu
    
    
    public static void adminDisplayMenu() {
    	System.out.println("\t\t\t ");
    	System.out.println("\t\t\t -----------ADMIN DISPLAY MENU------------");
    	System.out.println("\t\t\t --------------------------------------- ");
        System.out.println("\t\t\t|  1. Add new loan application          |");
        System.out.println("\t\t\t|  2. View all loan applications        |");
        System.out.println("\t\t\t|  3. Update application status         |");
        System.out.println("\t\t\t|  4. Exit                              |");
        System.out.println("\t\t\t ----------------------------------------");
        Scanner scan2 = new Scanner(System.in);
        int choice;
        System.out.println("Enter your Choice=");
        choice = scan2.nextInt();

        MenuChoice2(choice);
    }

    public static void MenuChoice2(int choice) {
        Scanner scan = new Scanner(System.in);
        Menu menu = new Menu();
        LoanCalculator lc = new LoanCalculator();

        switch (choice) {
            case 1:
                System.out.println("1.Add new loan application...");
                menu.displayLoan(getLoggedInUsername());
                break;
            case 2:
                System.out.println("2. View all loan applications");
                menu.payEMI(loggedInUsername, scan);
                break;
            case 3:
                System.out.println("3. Update application status ...");
                menu.updateProfile(scan);
                break;
            case 4:
                System.out.println("4.  Exiting...");
                System.exit(0); // Exit the application
                break;
            default:
                System.out.println("Invalid option. Please choose a valid option.");
                break;
        }
    }
    
    static String getLoggedInUsername() {
        return loggedInUsername; 
    }
   //User Login
    public static void login(Scanner scanner) throws IOException {
    	Scanner l1 = new Scanner(System.in);
    	System.out.print("\n 1. Login as Admin  ");
    	System.out.print("\n 2. Login as User  ");
    	int n = l1.nextInt();
    	if (n==1) {adminLogin(scanner);}else if(n==2){
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("------USER LOGIN----- ");
        System.out.print("\nEnter User Name : ");
        String username = br.readLine();
        System.out.print("\nEnter your Password: ");
        String password = br.readLine();
        
        

        try {
            Connection con = DBFunction.connectDB("mysql", "root", "root");
            if (con != null) {
                String selectQuery = "select * from user_tbl where userName=? and password=?";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setString(1, username);
                pstmt.setString(2, DigestUtils.sha256Hex(password));
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Welcome User " + username + " to the LoanPro Application");
                    loggedInUsername = username; // Set the logged-in username
                    while(true) {
                    	displayMenu();
                    }
                    
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                    login(scanner);
                }

                con.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    	}else {
    		System.out.print("Invalid Choice ( Choose 1 or 2)! ");
    		login(scanner);
    	}
    }
    
    
    //Admin Login
    public static void adminLogin(Scanner scanner) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("------ADMIN LOGIN----- ");
        System.out.print("\nEnter User Name : ");
        String a_username = br.readLine().trim();  // Trim input to remove extra spaces
        System.out.print("\nEnter your Password: ");
        String a_password = br.readLine().trim();  // Trim input to remove extra spaces

        try {
            Connection con = DBFunction.connectDB("mysql", "root", "root");
            if (con != null) {
                String selectQuery = "SELECT * FROM admin WHERE admin_name=? AND admin_password=?";
                PreparedStatement pstmt = con.prepareStatement(selectQuery);
                pstmt.setString(1, a_username);
                pstmt.setString(2, DigestUtils.sha256Hex(a_password));  // Hash password for comparison
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Welcome Admin " + a_username + " to the LoanPro Application");
                    loggedInUsername = a_username; // Set the logged-in username
                    while (true) {
                        adminDisplayMenu();
                    }

                } else {
                    System.out.println("Invalid credentials. Please try again.");
                    adminLogin(scanner);
                }

                con.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }

//Register User Admin
    public static void register(Scanner scanner) throws IOException {
    	Scanner sc1 = new Scanner(System.in);
    	System.out.println("\nRegister as : ");
    	System.out.println("\n1. Admin : ");
    	System.out.println("\n2. User : ");
    	int r=sc1.nextInt();
    	if(r == 1) {
    		System.out.println("\n2.Admin Registration : ");
    		System.out.println("\nEnter your Name: ");
            String a_username = scanner.nextLine();

            System.out.println("\nEnter password: ");
            String a_password = scanner.nextLine();

            System.out.println("\nRenter password: ");
            String a_confirmedPassword = scanner.nextLine();
            if (a_password.equals(a_confirmedPassword)) {
                try {
                	String hashedPassword = DigestUtils.sha256Hex(a_password);
                    Connection con = DBFunction.connectDB("mysql", "root", "root");
                    if (con != null) {
                        String insertQuery = "insert into admin (admin_name,admin_password) values (?,?)";
                        PreparedStatement pstmt = con.prepareStatement(insertQuery);

                        pstmt.setString(1, a_username);
                        pstmt.setString(2, hashedPassword);
                        int insertStatus = pstmt.executeUpdate();

                        if (insertStatus > 0) {
                            System.out.println(insertStatus + " Record Inserted Successfully");
                            if (con != null) {
            		        	String selectQuery = "SELECT admin_id, admin_name, admin_password " +
            		                     "FROM admin " +
            		                     "WHERE admin_name=?";
            		            PreparedStatement pstmt2 = con.prepareStatement(selectQuery);
            		            pstmt2.setString(1, a_username);
            		            ResultSet rs = pstmt2.executeQuery();

            		            System.out.println("Your Registered Details:");
            		            System.out.println("ID\t USER NAME");

            		            while (rs.next()) {
            		                int id = rs.getInt("admin_id");
            		                String userName = rs.getString("admin_name");
            		                
            		                
            		                

            		                System.out.println(id + "\t" + id + "\t" + userName);
            		                
            		            }

            		            
            		        } else
    							System.out.println("Failed to connect to the database.");
                            System.out.println("Enter 1 to login and 2 to exit :");
            				int n=scanner.nextInt();
            				
            				if(n==1)
            				{
            					login(scanner);
            				}
            				else
            				{
            					System.out.println("Exited Sucessfully");
            					System.exit(0);	
            				}
                            
                        } else {
                            System.out.println("Error inserting record.");
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
                System.out.println("Please try again!");
                register(scanner);
            }
        	
    	}
    	else if(r==2){
        System.out.println("\n1.User Registration : ");
        System.out.println("\nEnter your Name: ");
        String username = scanner.nextLine();

        System.out.println("\nEnter password: ");
        String password = scanner.nextLine();

        System.out.println("\nRenter password: ");
        String confirmedPassword = scanner.nextLine();
        
        System.out.println("\nEnter DOB: ");
        String dob = scanner.nextLine();
        
        System.out.println("\nEnter Address: ");
        String address = scanner.nextLine();
        
        System.out.println("\nEnter PAN: ");
        String pan = scanner.nextLine();
        
        System.out.println("\nEnter email: ");
        String email = scanner.nextLine();

        if (password.equals(confirmedPassword)) {
            try {
            	String hashedPassword = DigestUtils.sha256Hex(password);
                Connection con = DBFunction.connectDB("mysql", "root", "root");
                if (con != null) {
                    String insertQuery = "insert into user_tbl (userName,password,dob,address,pan,email) values (?,?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(insertQuery);

                    pstmt.setString(1, username);
                    pstmt.setString(2, hashedPassword);
                    pstmt.setString(3, dob);
                    pstmt.setString(4, address);
                    pstmt.setString(5, pan);
                    pstmt.setString(6, email);
                    int insertStatus = pstmt.executeUpdate();

                    if (insertStatus > 0) {
                        System.out.println(insertStatus + " Record Inserted Successfully");
                        if (con != null) {
        		        	String selectQuery = "SELECT id, userName, dob, address, pan , email ,timestamp " +
        		                     "FROM user_tbl " +
        		                     "WHERE userName=?";
        		            PreparedStatement pstmt2 = con.prepareStatement(selectQuery);
        		            pstmt2.setString(1, username);
        		            ResultSet rs = pstmt2.executeQuery();

        		            System.out.println("Your Registered Details:");
        		            System.out.println("ID\t USER NAME\tDOB\tADDRESS\tPAN \tEMAIL \tTIMESTAMP");

        		            while (rs.next()) {
        		                int id = rs.getInt("id");
        		                String userName = rs.getString("userName");
        		                String dob1 = rs.getString("dob");
        		                String address1 = rs.getString("address");
        		                String pan1 = rs.getString("pan");
        		                String email1 = rs.getString("email");
        		                
        		                java.sql.Timestamp timestamp = rs.getTimestamp("timestamp");

        		                
        		                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		                String formattedTimestamp = dateFormat.format(timestamp);
        		                

        		                System.out.println(id + "\t" + id + "\t" + userName + "\t\t" + dob1 + "\t\t" + address1 + "\t\t" + pan1 +"\t\t" + email +"\t\t" + formattedTimestamp);
        		                
        		            }

        		            
        		        } else
							System.out.println("Failed to connect to the database.");
                        System.out.println("Enter 1 to login and 2 to exit :");
        				int n=scanner.nextInt();
        				
        				if(n==1)
        				{
        					login(scanner);
        				}
        				else
        				{
        					System.out.println("Exited Sucessfully");
        					System.exit(0);	
        				}
                        
                    } else {
                        System.out.println("Error inserting record.");
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
            System.out.println("Please try again!");
            register(scanner);
        }
    	}
    	else {
    		System.out.print("\nInvalid Choice (Choose 1 or 2! ");
    	}
    }
}
