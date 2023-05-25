package com.example.KataBanking.service;

import com.example.KataBanking.exception.AccountNotFoundException;
import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Account;
import com.example.KataBanking.model.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);
    List<AccountDto> getAccounts();

    AccountDto deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException;

    AccountDto withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException;
    List<TransactionDto> getAccountTransactions(String accountNumber);

}
