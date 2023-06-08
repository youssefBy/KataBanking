package com.example.KataBanking.service.design_patterns.factory;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BasicAccount extends Account{

    public BasicAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
    }

    @Override
    public String getAccountType() {
        return "Basic Account";
    }
}
