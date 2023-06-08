package com.example.KataBanking.service.design_patterns.adapter;

public class LegacyAccount {

    private double balance;

    public void credit(double amount) {
        balance += amount;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    public double getAmount() {
        return balance;
    }
}
