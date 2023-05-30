package com.example.KataBanking.service;

import com.example.KataBanking.converter.AccountConverter;
import com.example.KataBanking.converter.AccountDtoConverter;
import com.example.KataBanking.converter.TransactionConverter;
import com.example.KataBanking.exception.AccountNotFoundException;
import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Account;
import com.example.KataBanking.model.entity.Transaction;
import com.example.KataBanking.enums.OperationType;
import com.example.KataBanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountDtoConverter.newInstance().convert(accountDto);
        return AccountConverter.newInstance().convert(accountRepository.save(account));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountDto> getAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream().map(account -> AccountConverter.newInstance().convert(account)).collect(Collectors.toList());
    }

    @Override
    public AccountDto deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty())
            throw new AccountNotFoundException(accountNumber);
        else{
            Account account = accountOpt.get();
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }

            account.setBalance(account.getBalance().add(amount));
            account.getTransactions().add(new Transaction(OperationType.DEPOSIT, amount, account.getBalance(), account));
            Account accountUpdated = accountRepository.save(account);
            return AccountConverter.newInstance().convert(accountUpdated);
        }
    }

    @Override
    public AccountDto withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isEmpty())
            throw new AccountNotFoundException("Account not found");
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
            Account accountUpdated = accountRepository.save(account);
            return AccountConverter.newInstance().convert(accountUpdated);
        }
    }


    @Override
    public List<TransactionDto> getAccountTransactions(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isEmpty())
            return Collections.emptyList();
        else{
            List<Transaction> transactions = account.get().getTransactions();
            return transactions.stream().map(transaction -> TransactionConverter.newInstance().convert(transaction)).collect(Collectors.toList());
        }
    }
}
