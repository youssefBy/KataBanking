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
public class AccountContoller {

    @Autowired
    private AccountService accountService;

    @PostMapping("/v0/accounts")
    public ResponseEntity<Account> createAccount(
            @RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/v0/accounts")
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @PostMapping("/v0/deposit")
    public ResponseEntity<Account> depositAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) {
        Account account = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/v0/withdraw")
    public ResponseEntity<Account> withdrawAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) {
        Account account = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/v0/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactions(
            @PathVariable String accountNumber) {
        List<Transaction> transactions = accountService.getAccountTransactions(accountNumber);
        return ResponseEntity.ok(transactions);
    }


}
