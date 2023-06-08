package com.example.KataBanking.service.design_patterns.singleton;

import com.example.KataBanking.model.dto.TransactionDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    private static Account instance;

    private String accountNumber;
    private BigDecimal balance;
    private List<TransactionDto> transactions;

    private Account() {
        this.balance = new BigDecimal(0);
        this.transactions = new ArrayList<>();
    }

    public static Account getInstance() {
        if (instance == null){
            instance = new Account();
        }
        return instance;
    }
}
