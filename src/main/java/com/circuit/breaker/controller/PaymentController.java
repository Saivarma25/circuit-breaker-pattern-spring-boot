package com.circuit.breaker.controller;

import com.circuit.breaker.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static final String TESTED = "TESTED";

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/circuit/breaker/{size}")
    public ResponseEntity<String> demoCircuitBreakerPattern(@PathVariable("size") Integer loopSize) {
        return ResponseEntity.ok(paymentService.makePaymentPrimary(loopSize));
    }

}
