package com.example.KataBanking.controller;

import com.example.KataBanking.model.dto.AccountDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountIntegrationTest {
    private MockMvc mockMvc;


    @Autowired
    private AccountContoller accountContoller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountContoller).build();
    }

    private AccountDto getAccount() {
        return AccountDto.builder()
                .id(1L)
                .accountNumber("123")
                .balance(BigDecimal.valueOf(0))
                .transactions(new ArrayList<>())
                .build();
    }

    @Test
    void should_create_account() throws Exception {
        AccountDto accountDto = getAccount();

        ObjectMapper mapper = new ObjectMapper();

        String requestJson = mapper.writeValueAsString(accountDto);


        mockMvc.perform(post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());

    }

    @Test
    void should_get_all_account() throws Exception {

        mockMvc.perform(get("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
    }


    @Test
    void should_get_account_transactions_by_account_number() throws Exception {

        mockMvc.perform(get("/api/v1/accounts/{accountNumber}/transactions", 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isOk());


    }
}