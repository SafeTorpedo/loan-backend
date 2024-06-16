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

    public PaymentStatus checkAndUpdateCreditScore(Loan loan, Date paymentDate) {
        repayLoan(loan, paymentDate);

        // Determine if payment is on time or late
        Date dueDate = loan.calculateDueDate();
        if (paymentDate.before(dueDate)) {
            return new PaymentStatus(true, "On-time");
        } else {
            return new PaymentStatus(false, "Late");
        }
    }

    private void repayLoan(Loan loan, Date paymentDate) {
        loan.setLoanStatus("FULFILLED");
        User applicant = loan.getApplicant();
        Date dueDate = loan.calculateDueDate();

        if (paymentDate.before(dueDate)) {
            applicant.increaseCreditScore(); // Increase credit score for on-time payment
        } else {
            double penaltyMultiplier = loan.getPenalty(); // Assuming penalty multiplier is directly the penalty amount
            applicant.decreaseCreditScore(penaltyMultiplier); // Decrease credit score based on penalty for late payment
        }
    }

    public static class PaymentStatus {
        private boolean onTime;
        private String status;

        public PaymentStatus(boolean onTime, String status) {
            this.onTime = onTime;
            this.status = status;
        }

        public boolean isOnTime() {
            return onTime;
        }

        public void setOnTime(boolean onTime) {
            this.onTime = onTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
