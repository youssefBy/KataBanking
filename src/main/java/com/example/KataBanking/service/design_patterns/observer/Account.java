package com.example.KataBanking.service.design_patterns.observer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Account {

    private String accountNumber;
    private BigDecimal balance;
    private List <AccountObserver> observers;

    public Account(String accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.observers = new ArrayList<>();
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        notifyObservers();
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
            notifyObservers();
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (AccountObserver observer : observers) {
            observer.update(balance);
        }
    }
}
