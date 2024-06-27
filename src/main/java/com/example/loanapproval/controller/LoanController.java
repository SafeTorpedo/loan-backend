package com.example.loanapproval.controller;

import com.example.loanapproval.model.*;
import com.example.loanapproval.service.LoanService;
import org.springframework.web.bind.annotation.*;
import com.example.loanapproval.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@CrossOrigin
@RestController
public class LoanController {

    @Autowired
    public UserRepository userRepository;
    private LoanService loanService = new LoanService();

    public List<Loan> loans = new ArrayList<>();

    @GetMapping("/loans")
    public List<Loan> getLoans() {

        List<User> users = userRepository.findAll();
        if (loans.isEmpty())
            loans = new ArrayList<>(Arrays.asList(
                    new CarLoan(1L, 50000, users.get(0), new Date(), 2),
                    new EducationLoan(2L, 30000, users.get(1), new Date(), 1),
                    new HomeLoan(3L, 100000, users.get(0), new Date(), 10)));

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
        for (Loan loan : loans) {
            if (Objects.equals(loan.getLoanId(), loanId)) {
                return loan;
            }
        }
        return null;
    }



    // endpoint to check loan due date
    @GetMapping("/loans/{loanId}/due-date")
    public Date getDueDate(@PathVariable Long loanId) {
        Loan loan = getLoanById(loanId);
        if (loan == null)
            return null;
        return loan.calculateDueDate();
    }

    // new loan
    @PostMapping("/loans/add")
    public Loan addLoan(@RequestBody LoanRequest loanRequest) {
        Optional<User> user = userRepository.findById(loanRequest.getUserId());
        if (user.isPresent()) {
            Loan newLoan = null;
            switch (loanRequest.getLoanType().toLowerCase()) {
                case "car":
                    newLoan = new CarLoan((long) loans.size() + 1, loanRequest.getLoanAmount(), user.get(), new Date(), loanRequest.getTenure());
                    break;
                case "edu":
                    newLoan = new EducationLoan((long) loans.size() + 1, loanRequest.getLoanAmount(), user.get(), new Date(), loanRequest.getTenure());
                    break;
                case "home":
                    newLoan = new HomeLoan((long) loans.size() + 1, loanRequest.getLoanAmount(), user.get(), new Date(), loanRequest.getTenure());
                    break;
            }
            if (newLoan != null) {
                loans.add(newLoan);
                return newLoan;
            }
        }
        return null;
    }
}

class LoanRequest {
    private Long userId;
    private double loanAmount;
    private String loanType;
    private int tenure;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }
}
