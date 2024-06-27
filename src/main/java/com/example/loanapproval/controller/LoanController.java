package com.example.loanapproval.controller;

import com.example.loanapproval.model.*;
import com.example.loanapproval.service.LoanService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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
        return loan.calculateDueDate();
    }

    // new loan
    @GetMapping("/loans/add/{userId}/{loanAmount}/{loanType}/{tenure}")
    public Loan addLoan(@PathVariable Long userId, @PathVariable double loanAmount, @PathVariable String loanType,
            @PathVariable int tenure) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Loan newLoan = null;
            switch (loanType) {
                case "car":
                    newLoan = new CarLoan((long) loans.size() + 1, loanAmount, user.get(), new Date(), tenure);
                    break;
                case "edu":
                    newLoan = new EducationLoan((long) loans.size() + 1, loanAmount, user.get(), new Date(), tenure);
                    break;
                case "home":
                    newLoan = new HomeLoan((long) loans.size() + 1, loanAmount, user.get(), new Date(), tenure);
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
