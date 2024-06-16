package com.example.loanapproval.model;

public class User {
    private Long userId;
    private String name;
    private String email;
    private int creditScore;

    public User(Long userId, String name, String email, int creditScore) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.creditScore = creditScore;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public void increaseCreditScore() {
        this.creditScore += 10; // Increase credit score
    }

    public void decreaseCreditScore(double penaltyMultiplier) {
        this.creditScore -= (int) (penaltyMultiplier * 10); // Decrease credit score based on penalty multiplier
    }
}
