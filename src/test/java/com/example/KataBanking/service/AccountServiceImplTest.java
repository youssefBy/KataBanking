package com.example.KataBanking.service;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;
import com.example.KataBanking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount() {
    }

    @Test
    void getAccounts() {
    }

    @Test
    void deposit() {
        String accountNumber = "123456789";
        BigDecimal balance = BigDecimal.valueOf(0);
        BigDecimal amount = BigDecimal.valueOf(100);
        List<Transaction> transactions = new ArrayList<>();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setTransactions(transactions);
        Optional<Account> optionalAccount = Optional.of(account);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);

        Account result = accountService.deposit(accountNumber, amount);

        assertEquals(account, result);
        assertEquals(amount, account.getBalance());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void withdraw() {
        String accountNumber = "123456789";
        BigDecimal balance = BigDecimal.valueOf(100);
        BigDecimal amount = BigDecimal.valueOf(100);
        List<Transaction> transactions = new ArrayList<>();
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setTransactions(transactions);
        Optional<Account> optionalAccount = Optional.of(account);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);

        Account result = accountService.withdraw(accountNumber, amount);

        assertEquals(account, result);
        assertEquals(balance.subtract(amount), account.getBalance());
        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
    }

    @Test
    void getAccountTransactions() {
    }
}