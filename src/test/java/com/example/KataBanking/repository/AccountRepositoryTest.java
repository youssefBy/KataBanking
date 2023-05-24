package com.example.KataBanking.repository;

import com.example.KataBanking.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @AfterEach
    void tearDown() {
       accountRepository.deleteAll();
    }


    @Test
    void shouldFindAccountByAccountNumber() {
        //given
        String accountNumber = "20";
        BigDecimal balance = BigDecimal.valueOf(0);

        Account account = Account.builder()
                .id(1L)
                .accountNumber(accountNumber)
                .balance(balance)
                .transactions(new ArrayList<>())
                .build();
        accountRepository.save(account);

        //when
        Optional<Account> expectedAccount = accountRepository.findByAccountNumber(accountNumber);

        //then
        assertEquals(expectedAccount.get().getAccountNumber(), accountNumber);

    }

    @Test
    void shouldNotFindAccountByAccountNumber() {
        //given
        String accountNumber = "20";

        //when
        Account expectedAccount = accountRepository.findByAccountNumber(accountNumber).orElse(null);

        //then
        assertNull(expectedAccount);

    }
}