package com.example.KataBanking.controller;

import com.example.KataBanking.exception.AccountNotFoundException;
import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.entity.Account;
import com.example.KataBanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class OperationController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/v1/operations/deposit")
    public ResponseEntity<AccountDto> depositAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) {
        try {
            AccountDto account = accountService.deposit(accountNumber, amount);
            return ResponseEntity.ok(account);
        }catch (AccountNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account Not Found", e);
        }

    }

    @PostMapping("/v1/operations/withdraw")
    public ResponseEntity<AccountDto> withdrawAmount(
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("amount") BigDecimal amount) throws AccountNotFoundException {
        try {
            AccountDto account = accountService.withdraw(accountNumber, amount);
            return ResponseEntity.ok(account);
        }catch (AccountNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Account Not Found", e);
        }
    }
}
