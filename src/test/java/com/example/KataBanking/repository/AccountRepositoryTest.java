package com.example.KataBanking.repository;

import com.example.KataBanking.model.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;


    @BeforeEach
    void beforeEach(){
        Account account = Account.builder()
                .id(1L)
                .accountNumber("20")
                .balance(BigDecimal.valueOf(0))
                .transactions(new ArrayList<>())
                .build();
        accountRepository.save(account);
    }

    @Test

    void testScript(){
        Long counts = accountRepository.count();

        assertEquals(4, counts);
    }

    @Test
    void should_find_account_by_account_number() {
        //given
        String accountNumber = "3";

        //when
        Optional<Account> expectedAccount = accountRepository.findByAccountNumber(accountNumber);

        //then
        assertTrue(expectedAccount.isPresent());
        assertEquals(expectedAccount.get().getAccountNumber(), accountNumber);

    }

    @Test
    void shouldNotFindAccountByAccountNumber() {
        //given
        String accountNumber = "70";

        //when
        var expectedAccount = accountRepository.findByAccountNumber(accountNumber);

        //then
        assertTrue(expectedAccount.isEmpty());

    }
}