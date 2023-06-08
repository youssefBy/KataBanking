package com.example.KataBanking.service.design_patterns.adapter;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class LegacyAccountAdapter implements Account {
    private LegacyAccount legacyAccount;


    @Override
    public void deposit(BigDecimal amount) {
        legacyAccount.credit(amount.doubleValue());
    }

    @Override
    public void withdraw(BigDecimal amount) {
        legacyAccount.debit(amount.doubleValue());
    }

    @Override
    public BigDecimal getBalance() {
        return BigDecimal.valueOf(legacyAccount.getAmount());
    }
}
