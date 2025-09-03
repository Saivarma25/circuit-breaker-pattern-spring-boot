package com.circuit.breaker.controller;

import com.circuit.breaker.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for circuit breaker application
 *
 * @author Saivarma Akarapu
 */
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * API to check circuit breaker
     * @param amount For transactions using gateways
     * @return Response from the gateway used (Either primary or secondary)
     */
    @GetMapping("/circuit/breaker/{amount}")
    public ResponseEntity<String> demoCircuitBreakerPattern(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(paymentService.makePaymentPrimary(amount));
    }

}
