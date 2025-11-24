package com.example.demo.POJO;
import java.time.LocalDateTime;

public class CardNumberResponse {
    private String cardNumber;
    private String productId;
    private LocalDateTime generatedAt;

    public CardNumberResponse(String cardNumber, String productId) {
        this.cardNumber = cardNumber;
        this.productId = productId;
        this.generatedAt = LocalDateTime.now();
    }

    // Getters y Setters
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}