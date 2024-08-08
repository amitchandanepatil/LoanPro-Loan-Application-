package com.axis.db;

import java.util.Scanner;

public class LoanCalculator {
	static int loanAmount;
	static double interestRate;
	static int loanTerm;
	static double emi;
	
	
	public static void loanCalculator()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("** Welcome to Loan Pro Calculator ***");
		System.out.println();
		
		System.out.println("Enter Loan Amount(Principal): ");
		loanAmount=scanner.nextInt();
		
		System.out.println("Enter Loan Term(in month): ");
		loanTerm=scanner.nextInt();
		
		System.out.println("Enter Annual Interest Rate: ");
		interestRate=scanner.nextDouble();
		
		double monthlyInterestRate = interestRate / 12 / 100;
	    int numberOfPayments = loanTerm;
	    double emi = (loanAmount * monthlyInterestRate) /
	                 (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
	    
	    System.out.println("You Loan EMI per month will be Rs. "+emi);
		
	}

}