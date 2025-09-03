package com.circuit.breaker.service;

import com.circuit.breaker.dao.PrimaryGateway;
import com.circuit.breaker.dao.SecondaryGateway;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service clas for circuit breaker and payment transactions
 *
 * @author Saivarma Akarapu
 */
@Service
public class PaymentService {

    // logger
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private static final String PAYMENT_SERVICE = "payment-service";

    private final PrimaryGateway primaryGateway;

    private final SecondaryGateway secondaryGateway;

    // Constructor injection
    public PaymentService(PrimaryGateway primaryGateway,
                          SecondaryGateway secondaryGateway) {
        this.primaryGateway = primaryGateway;
        this.secondaryGateway = secondaryGateway;
    }

    /**
     * Circuit Breaker method that handles failures with primary gateway if any and redirects the
     * transactions to secondary gateway when needed (CLOSE, OPEN and HALF-OPEN)
     * @param amountToPay Some integer to make a transaction
     * @return Response from primary gateway otherwise secondary gateways
     */
    @CircuitBreaker(name = PAYMENT_SERVICE, fallbackMethod = "fallbackMethod")
    public String makePaymentPrimary(int amountToPay) {

        // Primary Gateway call
        log.info("Inside primary gateway call");
        primaryGateway.addAmount(amountToPay);
        return "Transaction succeeded with primary gateway";
    }

    private String fallbackMethod(int amountToPay, Throwable t) {

        // secondary call
        log.info("Primary gateway failed: {}, Inside secondary gateway call", t.getMessage());
        secondaryGateway.addAmount(amountToPay);
        return "Transaction succeeded with Secondary gateway";
    }

}
