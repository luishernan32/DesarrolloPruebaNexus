package com.example.demo.service;

import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Card;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // 1. Generar número de tarjeta
    public String generateCardNumber(String productId) {
        if (!isValidProductId(productId)) {
            throw new IllegalArgumentException("Product ID must be exactly 6 digits");
        }
        return productId + generateRandomDigits(10);
    }

    // 2. Activar tarjeta
    public Card activateCard(String cardNumber, String cardHolderName) {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isPresent()) {
            Card card = existingCard.get();

            if (card.isActive()) {
                throw new IllegalStateException("Card is already activated");
            }

            if (card.isBlocked()) {
                throw new IllegalStateException("Card is blocked and cannot be activated");
            }

            card.setActive(true);
            card.setActivationDate(LocalDateTime.now());
            card.setCardHolderName(cardHolderName);

            return cardRepository.save(card);
        } else {
            String productId = cardNumber.substring(0, 6);
            Card newCard = new Card(cardNumber, productId, cardHolderName);
            newCard.setActive(true);
            newCard.setActivationDate(LocalDateTime.now());

            return cardRepository.save(newCard);
        }
    }

    // 3. Bloquear tarjeta
    public Card blockCard(String cardNumber) {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isEmpty()) {
            throw new IllegalArgumentException("Card not found");
        }

        Card card = existingCard.get();

        if (card.isBlocked()) {
            throw new IllegalStateException("Card is already blocked");
        }

        card.setBlocked(true);
        card.setActive(false);

        return cardRepository.save(card);
    }

    // 4. Recargar saldo - VERSIÓN SIMPLIFICADA
    public Card rechargeBalance(String cardNumber, BigDecimal amount) {
        // Validaciones básicas
        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException("Invalid card number");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        // Buscar tarjeta
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        // Validar estado
        if (!card.isActive()) {
            throw new IllegalStateException("Card is not active");
        }

        if (card.isBlocked()) {
            throw new IllegalStateException("Card is blocked");
        }

        // Recargar saldo
        BigDecimal newBalance = card.getBalance().add(amount);
        card.setBalance(newBalance);

        return cardRepository.save(card);
    }

    // Obtener saldo
    public BigDecimal getBalance(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));
        return card.getBalance();
    }

    // Métodos auxiliares
    private boolean isValidProductId(String productId) {
        return productId != null && productId.matches("\\d{6}");
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }



    // En la clase CardService, agregar este método:

    // 5. Consultar saldo
    public BigDecimal getCardBalance(String cardNumber) {
        // Validar formato del número
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        // Buscar la tarjeta
        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isEmpty()) {
            throw new IllegalArgumentException("Card not found");
        }

        Card card = existingCard.get();

        // Validar que la tarjeta esté activa
        if (!card.isActive()) {
            throw new IllegalStateException("Cannot check balance of an inactive card");
        }

        // Validar que la tarjeta no esté bloqueada
        if (card.isBlocked()) {
            throw new IllegalStateException("Cannot check balance of a blocked card");
        }

        return card.getBalance();
    }
}


/*package com.example.demo.service;

import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // Método existente para generar número
    public String generateCardNumber(String productId) {
        if (!isValidProductId(productId)) {
            throw new IllegalArgumentException("Product ID must be exactly 6 digits");
        }
        return productId + generateRandomDigits(10);
    }

    // Método existente para activar tarjeta
    public Card activateCard(String cardNumber, String cardHolderName) {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isPresent()) {
            Card card = existingCard.get();

            if (card.isActive()) {
                throw new IllegalStateException("Card is already activated");
            }

            if (card.isBlocked()) {
                throw new IllegalStateException("Card is blocked and cannot be activated");
            }

            card.activate();
            card.setCardHolderName(cardHolderName);

            return cardRepository.save(card);
        } else {
            String productId = cardNumber.substring(0, 6);
            Card newCard = new Card(cardNumber, productId, cardHolderName);
            newCard.activate();

            return cardRepository.save(newCard);
        }
    }

    // NUEVO MÉTODO: Bloquear tarjeta
    public Card blockCard(String cardNumber) {
        // Validar formato del número
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        // Buscar la tarjeta
        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isEmpty()) {
            throw new IllegalArgumentException("Card not found");
        }

        Card card = existingCard.get();

        // Verificar si ya está bloqueada
        if (card.isBlocked()) {
            throw new IllegalStateException("Card is already blocked");
        }

        // Bloquear la tarjeta
        card.block();

        return cardRepository.save(card);
    }

    // Métodos auxiliares
    private boolean isValidProductId(String productId) {
        return productId != null && productId.matches("\\d{6}");
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}*/


/*package com.example.demo.service;


import com.example.demo.model.Card;
import com.example.demo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    // Método existente para generar número
    public String generateCardNumber(String productId) {
        if (!isValidProductId(productId)) {
            throw new IllegalArgumentException("Product ID must be exactly 6 digits");
        }
        return productId + generateRandomDigits(10);
    }

    // Nuevo método para activar tarjeta
    public Card activateCard(String cardNumber, String cardHolderName) {
        // Validar formato del número de tarjeta
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalArgumentException("Invalid card number format");
        }

        // Buscar si la tarjeta ya existe
        Optional<Card> existingCard = cardRepository.findByCardNumber(cardNumber);

        if (existingCard.isPresent()) {
            Card card = existingCard.get();

            // Verificar si ya está activa
            if (card.isActive()) {
                throw new IllegalStateException("Card is already activated");
            }

            // Verificar si está bloqueada
            if (card.isBlocked()) {
                throw new IllegalStateException("Card is blocked and cannot be activated");
            }

            // Activar la tarjeta
            card.setActive(true);
            card.setActivationDate(LocalDateTime.now());
            card.setCardHolderName(cardHolderName);

            return cardRepository.save(card);
        } else {
            // Crear nueva tarjeta y activarla
            String productId = cardNumber.substring(0, 6);
            Card newCard = new Card(cardNumber, productId, cardHolderName);
            newCard.setActive(true);
            newCard.setActivationDate(LocalDateTime.now());

            return cardRepository.save(newCard);
        }
    }

    private boolean isValidProductId(String productId) {
        return productId != null && productId.matches("\\d{6}");
    }

    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}*/