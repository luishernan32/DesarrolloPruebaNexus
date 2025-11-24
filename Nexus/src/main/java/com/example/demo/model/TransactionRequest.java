package com.example.demo.model;

import java.math.BigDecimal;

public class TransactionRequest {
    private String cardId;
    private BigDecimal price;

    // Constructores
    public TransactionRequest() {}

    public TransactionRequest(String cardId, BigDecimal price) {
        this.cardId = cardId;
        this.price = price;
    }

    // Getters y Setters
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
