package com.example.KataBanking.model.dto;

import com.example.KataBanking.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder()
@AllArgsConstructor()
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private OperationType operation;
    private BigDecimal amount;
    private BigDecimal balance;
    private LocalDateTime date;

}
