package com.example.KataBanking.service.design_patterns.decorator;

import java.math.BigDecimal;

public class Account implements IAccount{
    private BigDecimal balance;

    public Account(BigDecimal balance) {
        this.balance = balance;
    }


    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        System.out.println("Deposit ok:" + amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
