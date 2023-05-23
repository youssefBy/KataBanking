package com.example.KataBanking.controller;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OperationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private OperationController operationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(operationController).build();
    }

    @Test
    void depositAmountTest() throws Exception {
        String accountNumber = "123456789";
        BigDecimal amount = BigDecimal.valueOf(100);
        Account account = new Account();
        account.setId(1L); // Set the account ID

        when(accountService.deposit(accountNumber, amount)).thenReturn(account);

        mockMvc.perform(post("/api/v0/operations/deposit")
                        .param("accountNumber", accountNumber)
                        .param("amount", amount.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1)) // Check the account ID
                .andExpect(jsonPath("$.name").doesNotExist());

        verify(accountService, times(1)).deposit(accountNumber, amount);
    }

    @Test
    void withdrawAmountTest() throws Exception {
        String accountNumber = "123456789";
        BigDecimal amount = BigDecimal.valueOf(100);
        Account account = new Account();
        account.setId(2L); // Set the account ID

        when(accountService.withdraw(accountNumber, amount)).thenReturn(account);

        mockMvc.perform(post("/api/v0/operations/withdraw")
                        .param("accountNumber", accountNumber)
                        .param("amount", amount.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2)) // Check the account ID
                .andExpect(jsonPath("$.name").doesNotExist());

        verify(accountService, times(1)).withdraw(accountNumber, amount);
    }
}