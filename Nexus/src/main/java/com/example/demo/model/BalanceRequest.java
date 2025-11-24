package com.example.demo.model;

import java.math.BigDecimal;

public class BalanceRequest {
    private String cardNumber;
    private BigDecimal balance;

    // Constructores
    public BalanceRequest() {}

    public BalanceRequest(String cardNumber, BigDecimal balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}