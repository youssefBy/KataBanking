package com.example.KataBanking.service.design_patterns.decorator;

import java.math.BigDecimal;

public class AccountWithLimitDecorator extends AccountDecorator{
    private BigDecimal limit;

    public AccountWithLimitDecorator(IAccount decoratedAccount, BigDecimal limit) {
        super(decoratedAccount);
        this.limit = limit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal availableBalance = getBalance().subtract(amount);
        if (availableBalance.compareTo(limit) >= 0) {
            decoratedAccount.withdraw(amount);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}
