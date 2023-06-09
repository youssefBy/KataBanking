package com.example.KataBanking.controller;

import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Account;
import com.example.KataBanking.model.entity.Transaction;
import com.example.KataBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountContoller {

    @Autowired
    private AccountService accountService;

    @PostMapping("/v1/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountDto> createAccount(
            @RequestBody AccountDto account){
        AccountDto newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/v1/accounts")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }



    @GetMapping("/v1/accounts/{accountNumber}/transactions")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<TransactionDto>> getAccountTransactions(
            @PathVariable String accountNumber) {
        List<TransactionDto> transactions = accountService.getAccountTransactions(accountNumber);
        return ResponseEntity.ok(transactions);
    }


}
