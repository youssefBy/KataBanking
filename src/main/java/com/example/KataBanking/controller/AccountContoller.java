package com.example.KataBanking.controller;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.entity.Transaction;
import com.example.KataBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountContoller {

    @Autowired
    private AccountService accountService;

    @PostMapping("/v1/accounts")
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/v1/accounts")
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }



    @GetMapping("/v1/accounts/{accountNumber}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(
            @PathVariable String accountNumber) {
        List<Transaction> transactions = accountService.getAccountTransactions(accountNumber);
        return ResponseEntity.ok(transactions);
    }


}
