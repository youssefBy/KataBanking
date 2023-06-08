package com.example.KataBanking.service.design_patterns.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor()
public class Account implements Cloneable{
    private String accountNumber;
    private BigDecimal balance;

    @Override
    public Account clone() throws CloneNotSupportedException {
        return (Account) super.clone();
    }
}
