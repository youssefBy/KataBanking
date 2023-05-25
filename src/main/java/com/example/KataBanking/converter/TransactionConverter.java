package com.example.KataBanking.converter;

import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Transaction;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class TransactionConverter implements Converter<Transaction, TransactionDto> {

    @Override
    public TransactionDto convert(Transaction transaction) {
        if(Objects.isNull(transaction)){
            return null;
        }

        return TransactionDto.builder()
                .id(transaction.getId())
                .operation(transaction.getOperation())
                .amount(transaction.getAmount())
                .balance(transaction.getBalance())
                .date(transaction.getDate())
                .build();
    }
}
