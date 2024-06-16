package com.example.loanapproval.model;

import java.util.Date;

public class CarLoan extends Loan {
    public CarLoan(Long loanId, double loanAmount, User applicant, Date applicationDate, int tenure) {
        super(loanId, loanAmount, applicant, applicationDate, 6.0, tenure, "CAR"); // Fixed interest rate for car loans
        this.penalty = 1.5; // Penalty for car loans
    }
}
