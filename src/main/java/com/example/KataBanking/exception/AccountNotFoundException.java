package com.example.KataBanking.exception;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(String message) {
        super("Account with account number "+message+" not found");
    }
}
