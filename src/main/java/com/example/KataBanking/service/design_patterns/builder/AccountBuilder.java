package com.example.KataBanking.service.design_patterns.builder;

import com.example.KataBanking.model.dto.TransactionDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountBuilder {
    private String accountNumber;
    private BigDecimal balance;


    public AccountBuilder() {
    }

    public AccountBuilder accounNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(this);
    }
}
