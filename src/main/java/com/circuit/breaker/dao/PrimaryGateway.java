package com.circuit.breaker.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Simulation of a gateway
 *
 * @author Saivarma Akarapu
 */
@Component
public class PrimaryGateway {

    private final Queue<Integer> amountQueue;

    public PrimaryGateway(@Value("${primary.gateway.value}") int value) {
        this.amountQueue = new ArrayBlockingQueue<>(value);
    }

    public void addAmount(int amount) {
        amountQueue.add(amount);
    }

}
