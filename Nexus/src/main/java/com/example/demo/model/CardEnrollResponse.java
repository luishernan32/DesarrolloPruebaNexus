package com.example.demo.model;



import java.time.LocalDateTime;

public class CardEnrollResponse {
    private String cardNumber;
    private String message;
    private boolean activated;
    private LocalDateTime activationDate;

    // Constructores
    public CardEnrollResponse() {}

    public CardEnrollResponse(String cardNumber, String message, boolean activated) {
        this.cardNumber = cardNumber;
        this.message = message;
        this.activated = activated;
        this.activationDate = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isActivated() { return activated; }
    public void setActivated(boolean activated) { this.activated = activated; }

    public LocalDateTime getActivationDate() { return activationDate; }
    public void setActivationDate(LocalDateTime activationDate) { this.activationDate = activationDate; }
}