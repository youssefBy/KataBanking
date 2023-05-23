package com.example.KataBanking.service;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;
import com.example.KataBanking.enums.OperationType;
import com.example.KataBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account deposit(String accountNumber, BigDecimal amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty())
            throw new RuntimeException("Account not found");
        else{
            Account account = accountOpt.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }

            account.setBalance(account.getBalance().add(amount));
            account.getTransactions().add(new Transaction(OperationType.DEPOSIT, amount, account.getBalance(), account));
            accountRepository.save(account);
            return account;
        }
    }

    @Override
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty())
            throw new RuntimeException("Account not found");
        else{
            Account account = accountOpt.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }

            if (amount.compareTo(account.getBalance()) > 0) {
                throw new IllegalArgumentException("Insufficient funds.");
            }

            account.setBalance(account.getBalance().subtract(amount));
            account.getTransactions().add(new Transaction(OperationType.WITHDRAWAL, amount, account.getBalance(), account));
            accountRepository.save(account);
            return account;
        }
    }


    @Override
    public List<Transaction> getAccountTransactions(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty())
            return Collections.emptyList();
        else
            return account.get().getTransactions();
    }
}
