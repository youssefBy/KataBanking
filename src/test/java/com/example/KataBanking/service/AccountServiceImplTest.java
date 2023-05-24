package com.example.KataBanking.service;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;
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

            Account account = Account.builder()
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

            assertEquals(capturedAccount, account);
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

            List<Transaction> result = accountService.getAccountTransactions(accountNumber);

            //then
            assertEquals(transactions, result);

        }

        @Test
        void shouldNotReturnTransactions() {
            //given
            String accountNumber = "20";

            //when
            List<Transaction> transactions = accountService.getAccountTransactions(accountNumber);

            //then
            assertEquals(Collections.emptyList(),transactions);

        }


    }




    @Nested
    class OperationsTest {
        @Test
        void shouldDeposit() {

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

            Account result = accountService.deposit(accountNumber, amount);

            //then
            assertEquals(account, result);
            assertEquals(amount, account.getBalance());
            verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        }

        @Test
        void shouldThrowExceptionWhenNoAccount() {
            //given
            String accountNumber = "20";
            BigDecimal amount = BigDecimal.valueOf(100);


            //then
            assertThrows(RuntimeException.class, () -> accountService.deposit(accountNumber, amount));
        }

        @Test
        void shouldWithdraw() {

            //given
            String accountNumber = "123456789";
            BigDecimal balance = BigDecimal.valueOf(100);
            BigDecimal amount = BigDecimal.valueOf(100);
            List<Transaction> transactions = new ArrayList<>();
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setBalance(balance);
            account.setTransactions(transactions);
            Optional<Account> optionalAccount = Optional.of(account);

            //when
            when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(optionalAccount);
            Account result = accountService.withdraw(accountNumber, amount);

            //then
            assertEquals(account, result);
            assertEquals(balance.subtract(amount), account.getBalance());
            verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        }
    }
}