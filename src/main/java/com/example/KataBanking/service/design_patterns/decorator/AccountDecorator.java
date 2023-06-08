package com.example.KataBanking.service.design_patterns.decorator;

import java.math.BigDecimal;

public abstract class AccountDecorator implements IAccount{
    protected IAccount decoratedAccount;

    public AccountDecorator(IAccount decoratedAccount) {
        this.decoratedAccount = decoratedAccount;
    }

    public BigDecimal getBalance() {
        return decoratedAccount.getBalance();
    }

    public void deposit(BigDecimal amount) {
        decoratedAccount.deposit(amount);
    }

    public void withdraw(BigDecimal amount) {
        decoratedAccount.withdraw(amount);
    }
}
