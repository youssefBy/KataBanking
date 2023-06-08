package com.example.KataBanking.service.design_patterns.factory;

import lombok.Data;

import java.math.BigDecimal;

@Data
public abstract class Account {

    protected String accountNumber;
    protected BigDecimal balance;


    public abstract String getAccountType();

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

}
