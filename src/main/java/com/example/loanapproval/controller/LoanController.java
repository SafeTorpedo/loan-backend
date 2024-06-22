package com.example.loanapproval.controller;

import com.example.loanapproval.model.*;
import com.example.loanapproval.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.loanapproval.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Objects;



@RestController
public class LoanController {
    
    @Autowired
    private UserRepository userRepository;
    private LoanService loanService = new LoanService();

    @GetMapping("/loans")
    public List<Loan> getLoans() {
        
        List<User> users = userRepository.findAll();
        
        List<Loan> loans = Arrays.asList(
                new CarLoan(1L, 50000, users.get(0), new Date(), 2),
                new EducationLoan(2L, 30000, users.get(1), new Date(), 1),
                new HomeLoan(3L, 100000, users.get(0), new Date(), 10)
        );

        for (Loan loan : loans) {
            loanService.approveLoan(loan);
        }
        return loans;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @GetMapping("/loans/{loanId}")
    public Loan getLoanById(@PathVariable Long loanId) {

        List<User> users = userRepository.findAll();
        List<Loan> loans = Arrays.asList(
                new CarLoan(1L, 50000, users.get(0), new Date(), 2),
                new EducationLoan(2L, 30000, users.get(1), new Date(), 1),
                new HomeLoan(3L, 100000, users.get(0), new Date(), 10)
        );
        for (Loan loan : loans) {
            if (Objects.equals(loan.getLoanId(), loanId)) {
                return loan;
            }
        }
        return null;
    }

    @GetMapping("/loans/{loanId}/payment/{paymentDate}")
    public LoanService.PaymentStatus getPaymentStatus(@PathVariable Long loanId, @PathVariable Date paymentDate) {
        Loan loan = getLoanById(loanId);
        return loanService.checkAndUpdateCreditScore(loan, paymentDate);
    }

    // endpoint to check loan due date
    @GetMapping("/loans/{loanId}/due-date")
    public Date getDueDate(@PathVariable Long loanId) {
        Loan loan = getLoanById(loanId);
        return loan.calculateDueDate();
    }

}
