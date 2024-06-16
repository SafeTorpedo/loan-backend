package com.example.loanapproval.model;

import java.util.Date;

public class HomeLoan extends Loan {
    public HomeLoan(Long loanId, double loanAmount, User applicant, Date applicationDate, int tenure) {
        super(loanId, loanAmount, applicant, applicationDate, 5.0, tenure, "HOME"); // Fixed interest rate for home loans
        this.penalty = 2.0; // Penalty for home loans
    }
}
