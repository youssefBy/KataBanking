package com.example.KataBanking.service.design_patterns.observer;

import java.math.BigDecimal;

public class BalanceLogger implements AccountObserver{
    @Override
    public void update(BigDecimal newBalance) {
        System.out.println("Balance update :" + newBalance);
    }
}
