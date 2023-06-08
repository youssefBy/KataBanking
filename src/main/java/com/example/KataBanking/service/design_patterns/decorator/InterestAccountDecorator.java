package com.example.KataBanking.service.design_patterns.decorator;

import java.math.BigDecimal;

public class InterestAccountDecorator extends AccountDecorator{
    private BigDecimal interestRate;

    public InterestAccountDecorator(IAccount decoratedAccount, BigDecimal interestRate) {
        super(decoratedAccount);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(BigDecimal amount) {
        BigDecimal interestAmount = amount.multiply(interestRate);
        decoratedAccount.deposit(amount.add(interestAmount));
    }
}
