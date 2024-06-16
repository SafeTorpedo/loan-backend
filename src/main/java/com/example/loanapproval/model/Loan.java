package com.example.loanapproval.model;

import java.util.Date;

public class Loan {
    protected Long loanId;
    protected double loanAmount;
    protected User applicant;
    protected String loanStatus;
    protected Date applicationDate;
    protected double interest;
    protected int tenure; // Updated to include tenure
    protected String loanType;
    protected double penalty;

    public Loan(Long loanId, double loanAmount, User applicant, Date applicationDate, double interest, int tenure, String loanType) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.applicant = applicant;
        this.loanStatus = "PENDING";
        this.applicationDate = applicationDate;
        this.interest = interest;
        this.tenure = tenure;
        this.loanType = loanType;
    }

    // Getters and Setters
    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Date calculateDueDate() {
        // Calculate the due date based on the application date and tenure
        return new Date(applicationDate.getTime() + (long) tenure * 30 * 24 * 60 * 60 * 1000); // assuming tenure is in months
    }
}
