package com.example.demo.model;


public class CardEnrollRequest {
    private String cardNumber;
    private String cardHolderName;

    // Constructor por defecto
    public CardEnrollRequest() {}

    // Constructor con parámetros
    public CardEnrollRequest(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardHolderName() { return cardHolderName; }
    public void setCardHolderName(String cardHolderName) { this.cardHolderName = cardHolderName; }
}
