package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class CardBlockResponse {
    private String cardNumber;
    private String message;
    private boolean blocked;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime blockedAt;

    // Constructores
    public CardBlockResponse() {}

    public CardBlockResponse(String cardNumber, String message, boolean blocked) {
        this.cardNumber = cardNumber;
        this.message = message;
        this.blocked = blocked;
        this.blockedAt = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }

    public LocalDateTime getBlockedAt() { return blockedAt; }
    public void setBlockedAt(LocalDateTime blockedAt) { this.blockedAt = blockedAt; }
}