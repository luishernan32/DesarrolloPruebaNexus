package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.CardService;
import com.example.demo.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    // 1. Generar número de tarjeta
    @GetMapping("/{productId}/number")
    public ResponseEntity<TransactionResponse.CardNumberResponse> generateCardNumber(
            @PathVariable String productId) {

        try {
            String cardNumber = cardService.generateCardNumber(productId);
            TransactionResponse.CardNumberResponse response = new TransactionResponse.CardNumberResponse(cardNumber, productId);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // 2. Activar tarjeta
    @PostMapping("/enroll")
    public ResponseEntity<CardEnrollResponse> activateCard(
            @RequestBody CardEnrollRequest request) {

        try {
            Card card = cardService.activateCard(
                    request.getCardNumber(),
                    request.getCardHolderName()
            );

            CardEnrollResponse response = new CardEnrollResponse(
                    card.getCardNumber(),
                    "Card successfully activated",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException | IllegalStateException e) {
            CardEnrollResponse response = new CardEnrollResponse(
                    request.getCardNumber(),
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 3. Bloquear tarjeta
    @DeleteMapping("/{cardId}")
    public ResponseEntity<CardBlockResponse> blockCard(
            @PathVariable String cardId) {

        try {
            Card card = cardService.blockCard(cardId);

            CardBlockResponse response = new CardBlockResponse(
                    card.getCardNumber(),
                    "Card successfully blocked",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            CardBlockResponse response = new CardBlockResponse(
                    cardId,
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);

        } catch (IllegalStateException e) {
            CardBlockResponse response = new CardBlockResponse(
                    cardId,
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 4. Recargar saldo - VERSIÓN SIMPLIFICADA
    @PostMapping("/balance")
    public ResponseEntity<?> rechargeBalance(@RequestBody BalanceRequest request) {
        try {
            Card card = cardService.rechargeBalance(request.getCardNumber(), request.getBalance());

            return ResponseEntity.ok().body(
                    "Balance recharged successfully. New balance: " + card.getBalance()
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // En la clase CardController, agregar este endpoint:

    // 5. Consultar saldo
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<BalanceQueryResponse> getCardBalance(
            @PathVariable String cardId) {

        try {
            BigDecimal balance = cardService.getCardBalance(cardId);

            BalanceQueryResponse response = new BalanceQueryResponse(
                    cardId,
                    balance,
                    "USD",
                    "Balance query successful",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            BalanceQueryResponse response = new BalanceQueryResponse(
                    cardId,
                    BigDecimal.ZERO,
                    "USD",
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);

        } catch (IllegalStateException e) {
            BalanceQueryResponse response = new BalanceQueryResponse(
                    cardId,
                    BigDecimal.ZERO,
                    "USD",
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
}


/*package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.TransactionResponse.CardNumberResponse;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    // Endpoint 1: Generar número de tarjeta
    @GetMapping("/{productId}/number")
    public ResponseEntity<CardNumberResponse> generateCardNumber(
            @PathVariable String productId) {

        try {
            String cardNumber = cardService.generateCardNumber(productId);
            CardNumberResponse response = new CardNumberResponse(cardNumber, productId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint 2: Activar tarjeta
    @PostMapping("/enroll")
    public ResponseEntity<CardEnrollResponse> activateCard(
            @RequestBody CardEnrollRequest request) {

        try {
            Card card = cardService.activateCard(
                    request.getCardNumber(),
                    request.getCardHolderName()
            );

            CardEnrollResponse response = new CardEnrollResponse(
                    card.getCardNumber(),
                    "Card successfully activated",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException | IllegalStateException e) {
            CardEnrollResponse response = new CardEnrollResponse(
                    request.getCardNumber(),
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Endpoint 3: Bloquear tarjeta
    @DeleteMapping("/{cardId}")
    public ResponseEntity<CardBlockResponse> blockCard(
            @PathVariable String cardId) {

        try {
            Card card = cardService.blockCard(cardId);

            CardBlockResponse response = new CardBlockResponse(
                    card.getCardNumber(),
                    "Card successfully blocked",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            CardBlockResponse response = new CardBlockResponse(
                    cardId,
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);

        } catch (IllegalStateException e) {
            CardBlockResponse response = new CardBlockResponse(
                    cardId,
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
}

*/

/*package com.example.demo.controller;


import com.example.demo.model.TransactionResponse.CardNumberResponse;
import com.example.demo.model.CardEnrollRequest;
import com.example.demo.model.*;
import com.example.demo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    // Endpoint existente para generar número
    @GetMapping("/{productId}/number")
    public ResponseEntity<CardNumberResponse> generateCardNumber(
            @PathVariable String productId) {

        try {
            String cardNumber = cardService.generateCardNumber(productId);
            CardNumberResponse response = new CardNumberResponse(cardNumber, productId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Nuevo endpoint para activar tarjeta
    @PostMapping("/enroll")
    public ResponseEntity<CardEnrollResponse> activateCard(
            @RequestBody CardEnrollRequest request) {

        try {
            Card card = cardService.activateCard(
                    request.getCardNumber(),
                    request.getCardHolderName()
            );

            CardEnrollResponse response = new CardEnrollResponse(
                    card.getCardNumber(),
                    "Card successfully activated",
                    true
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            CardEnrollResponse response = new CardEnrollResponse(
                    request.getCardNumber(),
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);

        } catch (IllegalStateException e) {
            CardEnrollResponse response = new CardEnrollResponse(
                    request.getCardNumber(),
                    "Error: " + e.getMessage(),
                    false
            );
            return ResponseEntity.badRequest().body(response);
        }
    }
}*/