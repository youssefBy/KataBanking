package com.example.KataBanking.service;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;
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
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty())
            throw new RuntimeException("Account not found");
        else{
            account.get().deposit(amount);
            accountRepository.save(account.get());
            return account.get();
        }
    }

    @Override
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty())
            throw new RuntimeException("Account not found");
        else{
            account.get().withdraw(amount);
            accountRepository.save(account.get());
            return account.get();
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
