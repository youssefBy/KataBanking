package com.example.KataBanking.model.entity;

import com.example.KataBanking.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Account account;

    private OperationType operation;
    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDateTime date;

    public Transaction(OperationType operation, BigDecimal amount, BigDecimal balance, Account account) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDateTime.now();
        this.account = account;
    }
}
