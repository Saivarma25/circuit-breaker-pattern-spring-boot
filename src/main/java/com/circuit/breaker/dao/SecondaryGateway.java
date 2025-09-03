package com.circuit.breaker.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;

@Component
public class SecondaryGateway {

    private final Queue<Integer> amountQueue;

    public SecondaryGateway(@Value("${secondary.gateway.value}") int value) {
        this.amountQueue = new ArrayDeque<>(value);
    }

    public void addAmount(int amount) {
        amountQueue.add(amount);
    }

    public Queue<Integer> getAmountQueue() {
        return amountQueue;
    }

}
