package com.example.KataBanking.controller;

import com.example.KataBanking.entity.Account;
import com.example.KataBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/v1/operations/deposit")
    public ResponseEntity<Account> depositAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) {
        Account account = accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/v1/operations/withdraw")
    public ResponseEntity<Account> withdrawAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) {
        Account account = accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok(account);
    }
}
