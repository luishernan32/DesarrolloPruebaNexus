package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BalanceQueryResponse {
    private String cardNumber;
    private BigDecimal balance;
    private String currency;
    private String message;
    private boolean success;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime queryDate;

    // Constructores
    public BalanceQueryResponse() {}

    public BalanceQueryResponse(String cardNumber, BigDecimal balance, String currency,
                                String message, boolean success) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.currency = currency;
        this.message = message;
        this.success = success;
        this.queryDate = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public LocalDateTime getQueryDate() { return queryDate; }
    public void setQueryDate(LocalDateTime queryDate) { this.queryDate = queryDate; }
}