package com.example.KataBanking.service;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account createAccount(Account account);
    List<Account> getAccounts();

    Account deposit(String accountNumber, BigDecimal amount);

    Account withdraw(String accountNumber, BigDecimal amount);
    List<Transaction> getAccountTransactions(String accountNumber);

}
