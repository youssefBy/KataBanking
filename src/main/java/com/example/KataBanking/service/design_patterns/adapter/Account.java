package com.example.KataBanking.service.design_patterns.adapter;

import java.math.BigDecimal;

public interface Account {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    BigDecimal getBalance();
}
