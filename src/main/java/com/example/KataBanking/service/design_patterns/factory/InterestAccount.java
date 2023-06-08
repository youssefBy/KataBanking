package com.example.KataBanking.service.design_patterns.factory;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InterestAccount extends Account{
    private BigDecimal interestRate;

    public InterestAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.interestRate = BigDecimal.valueOf(0.05);
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    @Override
    public String getAccountType() {
        return "Account with interests";
    }
}
