package com.example.loanapproval.service;

import com.example.loanapproval.model.Loan;
import com.example.loanapproval.model.User;

import java.util.Date;

public class LoanService {
    private static final int CREDIT_SCORE_THRESHOLD = 650;

    public Loan approveLoan(Loan loan) {
        if (loan.getApplicant().getCreditScore() < CREDIT_SCORE_THRESHOLD) {
            loan.setLoanStatus("REJECTED");
        } else {
            loan.setLoanStatus("APPROVED");
        }
        return loan;
    }

    // Overloaded method to process loan with additional parameter
    public Loan approveLoan(Loan loan, int customCreditScoreThreshold) {
        if (loan.getApplicant().getCreditScore() < customCreditScoreThreshold) {
            loan.setLoanStatus("REJECTED");
        } else {
            loan.setLoanStatus("APPROVED");
        }
        return loan;
    }

}
