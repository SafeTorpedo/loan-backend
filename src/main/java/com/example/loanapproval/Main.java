package com.example.loanapproval;

import com.example.loanapproval.model.*;
import com.example.loanapproval.service.LoanService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Calendar;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        // Create users
//        User user1 = new User(1L, "Alice", "alice@example.com", 700);
//        User user2 = new User(2L, "Bob", "bob@example.com", 600);
//
//        // Create loans with specific tenures
//        Loan carLoan = new CarLoan(1L, 50000, user1, new Date(), 2);
//        Loan educationLoan = new EducationLoan(2L, 30000, user2, new Date(), 1);
//        Loan homeLoan = new HomeLoan(3L, 100000, user1, new Date(), 10);
//
//        // LoanService instance
//        LoanService loanService = new LoanService();
//
//        // Approve loans
//        carLoan = loanService.approveLoan(carLoan);
//        educationLoan = loanService.approveLoan(educationLoan);
//        homeLoan = loanService.approveLoan(homeLoan);
//
//        // Print loan statuses
//        System.out.println("Car Loan Status: " + carLoan.getLoanStatus());
//        System.out.println("Education Loan Status: " + educationLoan.getLoanStatus());
//        System.out.println("Home Loan Status: " + homeLoan.getLoanStatus());
//
//        // Use overloaded method to check with custom credit score threshold
//        int customCreditScoreThreshold = 680;
//        carLoan = loanService.approveLoan(carLoan, customCreditScoreThreshold);
//        System.out.println("Car Loan Status with custom threshold: " + carLoan.getLoanStatus());
//
//        // Simulate loan repayment
//        Calendar calendar = Calendar.getInstance();
//
//        // On-time payment for car loan
//        calendar.add(Calendar.MONTH, 1); // 1 month later
//        Date carOnTimePaymentDate = calendar.getTime();
//        LoanService.PaymentStatus carPaymentStatus = loanService.checkAndUpdateCreditScore(carLoan, carOnTimePaymentDate);
//        System.out.println("User1 Credit Score after payment for Car Loan: " + user1.getCreditScore());
//        System.out.println("Car Loan Payment Status: " + (carPaymentStatus.isOnTime() ? "On-time" : "Late"));
//
//        // Late payment for education loan
//        calendar.add(Calendar.MONTH, 2); // 2 months later (total 3 months)
//        Date educationLatePaymentDate = calendar.getTime();
//        LoanService.PaymentStatus educationPaymentStatus = loanService.checkAndUpdateCreditScore(educationLoan, educationLatePaymentDate);
//        System.out.println("User2 Credit Score after payment for Education Loan: " + user2.getCreditScore());
//        System.out.println("Education Loan Payment Status: " + (educationPaymentStatus.isOnTime() ? "On-time" : "Late"));
    }
}
