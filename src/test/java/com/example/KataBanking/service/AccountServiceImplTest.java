package com.example.KataBanking.service;

import com.example.KataBanking.exception.AccountNotFoundException;
import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Account;
import com.example.KataBanking.model.entity.Transaction;
import com.example.KataBanking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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


    @Nested
    class AccountsTest {


        @Test
        void shouldCreateAccount() {
            //given
            String accountNumber = "20";
            BigDecimal balance = BigDecimal.valueOf(0);

            AccountDto account = AccountDto.builder()
                    .id(1L)
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(new ArrayList<>())
                    .build();

            //when
            accountService.createAccount(account);

            //then
            ArgumentCaptor<Account> accountArgumentCaptor =
                    ArgumentCaptor.forClass(Account.class);

            verify(accountRepository).save(accountArgumentCaptor.capture());

            Account capturedAccount = accountArgumentCaptor.getValue();

            assertEquals(capturedAccount.getAccountNumber(), account.getAccountNumber());
        }

        @Test
        void shouldGetAllAccounts() {
            //when
            accountService.getAccounts();
            //then
            verify(accountRepository).findAll();
        }


        @Test
        void shouldGetAccountTransactions() {
            //given
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(new Transaction());
            transactions.add(new Transaction());

            String accountNumber = "20";
            BigDecimal balance = BigDecimal.valueOf(0);

            Account account = Account.builder()
                    .id(1L)
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(transactions)
                    .build();


            //when
            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

            List<TransactionDto> result = accountService.getAccountTransactions(accountNumber);

            //then
            assertEquals(transactions.size(), result.size());
            /*BigDecimal reduce = transactions.stream().map(transaction -> transaction.getBalance()).reduce(BigDecimal.ZERO, BigDecimal::add);
            assertEquals(0, reduce);*/

        }

        @Test
        void shouldNotReturnTransactions() {
            //given
            String accountNumber = "20";

            //when
            List<TransactionDto> transactions = accountService.getAccountTransactions(accountNumber);

            //then
            assertEquals(Collections.emptyList(),transactions);

        }


    }




    @Nested
    class OperationsTest {
        @Test
        void shouldDepositWhenAccountEmpty() throws AccountNotFoundException {

            //given
            String accountNumber = "123456789";
            BigDecimal balance = BigDecimal.valueOf(0);
            BigDecimal amount = BigDecimal.valueOf(100);
            List<Transaction> transactions = new ArrayList<>();

            Account account = Account.builder()
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(transactions)
                    .build();


            Optional<Account> optionalAccount = Optional.of(account);

            //when
            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);

            AccountDto result = accountService.deposit(accountNumber, amount);

            //then
            assertEquals(amount, account.getBalance());
            verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        }

        @Test
        void shouldWithdraw() throws AccountNotFoundException {

            //given
            String accountNumber = "123456789";
            BigDecimal balance = BigDecimal.valueOf(100);
            BigDecimal amount = BigDecimal.valueOf(100);
            List<Transaction> transactions = new ArrayList<>();
            Account account = Account.builder()
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(transactions)
                    .build();

            Optional<Account> optionalAccount = Optional.of(account);

            //when
            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);
            AccountDto result = accountService.withdraw(accountNumber, amount);

            //then
            assertEquals(balance.subtract(amount), account.getBalance());
            verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        }

        @Test
        void shouldThrowExceptionWhenNoAccount() {
            //given
            String accountNumber = "20";
            BigDecimal amount = BigDecimal.valueOf(100);

            //then
            assertThrows(AccountNotFoundException.class, () -> accountService.deposit(accountNumber, amount));
            assertThrows(AccountNotFoundException.class, () -> accountService.withdraw(accountNumber, amount));
            verify(accountRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenAmountNegative() {
            //given
            String accountNumber = "123456789";
            BigDecimal balance = BigDecimal.valueOf(0);
            BigDecimal amount = BigDecimal.valueOf(-100);
            List<Transaction> transactions = new ArrayList<>();

            Account account = Account.builder()
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(transactions)
                    .build();


            Optional<Account> optionalAccount = Optional.of(account);

            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);

            //then
            assertThrows(IllegalArgumentException.class, () -> accountService.deposit(accountNumber, amount));
            assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(accountNumber, amount));
            verify(accountRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenAmountHigherThanBalance() {
            //given
            String accountNumber = "123456789";
            BigDecimal balance = BigDecimal.valueOf(0);
            BigDecimal amount = BigDecimal.valueOf(100);
            List<Transaction> transactions = new ArrayList<>();

            Account account = Account.builder()
                    .accountNumber(accountNumber)
                    .balance(balance)
                    .transactions(transactions)
                    .build();


            Optional<Account> optionalAccount = Optional.of(account);

            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);

            //then
            assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(accountNumber, amount));
            verify(accountRepository, never()).save(any());
        }
    }
}