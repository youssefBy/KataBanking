package com.example.KataBanking.service.design_patterns.builder;

import com.example.KataBanking.model.dto.TransactionDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Account {

    private String accountNumber;
    private BigDecimal balance;

    public Account(AccountBuilder builder) {
        this.accountNumber = builder.getAccountNumber();
        this.balance = builder.getBalance();
    }

}
