package com.example.KataBanking.entity;

import com.example.KataBanking.enums.OperationType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder()
@AllArgsConstructor()
@NoArgsConstructor()
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String accountNumber;
    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions;


}
