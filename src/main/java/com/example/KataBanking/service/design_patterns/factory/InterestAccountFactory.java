package com.example.KataBanking.service.design_patterns.factory;

public class InterestAccountFactory implements AccountFactory{

    @Override
    public Account createAccount(String accountNumber) {
        return new InterestAccount(accountNumber);
    }
}
