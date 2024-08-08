package com.axis.bean;

public class ApplyLoanBean {
    private double income;
    private double loanAmount;
    private int loanType;
    private int loanTerms;
    private int employmentStatus;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getLoanType() {
        return loanType;
    }

    public void setLoanType(int loanType) {
        this.loanType = loanType;
    }

    public int getLoanTerms() {
        return loanTerms;
    }

    public void setLoanTerms(int loanTerms) {
        this.loanTerms = loanTerms;
    }

    public int getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(int employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
}
