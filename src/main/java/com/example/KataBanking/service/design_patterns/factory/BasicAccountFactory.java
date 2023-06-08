package com.example.KataBanking.service.design_patterns.factory;

public class BasicAccountFactory implements AccountFactory{

    @Override
    public Account createAccount(String accountNumber) {
        return new BasicAccount(accountNumber);
    }
}
