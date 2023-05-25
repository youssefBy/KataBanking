package com.example.KataBanking.controller;

import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountContollerTest {
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountContoller accountContoller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountContoller).build();
    }

    private AccountDto getAccount() {
        return AccountDto.builder()
                .accountNumber("123")
                .balance(BigDecimal.valueOf(0))
                .transactions(new ArrayList<>())
                .build();
    }

    @Test
    void should_create_account() throws Exception {
        AccountDto accountDto = getAccount();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(accountDto);


        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

        verify(accountService, times(1)).createAccount(accountDto);
    }

    @Test
    void should_get_all_account() throws Exception {

        mockMvc.perform(get("/api/v1/accounts" )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());


        verify(accountService, times(1)).getAccounts();
    }


    @Test
    void should_get_account_transactions_by_account_number() throws Exception {

        mockMvc.perform(get("/api/v1/accounts/{accountNumber}/transactions", 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());


        verify(accountService, times(1)).getAccountTransactions(anyString());
    }
}