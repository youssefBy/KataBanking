package com.example.KataBanking.service.design_patterns.decorator;

import java.math.BigDecimal;

public interface IAccount {
    BigDecimal getBalance();
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
}
