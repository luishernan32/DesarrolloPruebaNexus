package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceResponse {
    private String cardNumber;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private BigDecimal rechargeAmount;
    private String message;
    private boolean success;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime rechargeDate;

    // Constructores
    public BalanceResponse() {}

    public BalanceResponse(String cardNumber, BigDecimal previousBalance,
                           BigDecimal newBalance, BigDecimal rechargeAmount,
                           String message, boolean success) {
        this.cardNumber = cardNumber;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.rechargeAmount = rechargeAmount;
        this.message = message;
        this.success = success;
        this.rechargeDate = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getPreviousBalance() { return previousBalance; }
    public void setPreviousBalance(BigDecimal previousBalance) { this.previousBalance = previousBalance; }

    public BigDecimal getNewBalance() { return newBalance; }
    public void setNewBalance(BigDecimal newBalance) { this.newBalance = newBalance; }

    public BigDecimal getRechargeAmount() { return rechargeAmount; }
    public void setRechargeAmount(BigDecimal rechargeAmount) { this.rechargeAmount = rechargeAmount; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public LocalDateTime getRechargeDate() { return rechargeDate; }
    public void setRechargeDate(LocalDateTime rechargeDate) { this.rechargeDate = rechargeDate; }
}