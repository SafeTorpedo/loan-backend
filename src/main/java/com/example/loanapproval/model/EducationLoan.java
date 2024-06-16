package com.example.loanapproval.model;

import java.util.Date;

public class EducationLoan extends Loan {
    public EducationLoan(Long loanId, double loanAmount, User applicant, Date applicationDate, int tenure) {
        super(loanId, loanAmount, applicant, applicationDate, 4.0, tenure, "EDUCATION"); // Fixed interest rate for education loans
        this.penalty = 1.0; // Penalty for education loans
    }
}
