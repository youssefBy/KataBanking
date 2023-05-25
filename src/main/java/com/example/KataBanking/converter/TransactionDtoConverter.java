package com.example.KataBanking.converter;

import com.example.KataBanking.model.dto.TransactionDto;
import com.example.KataBanking.model.entity.Transaction;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;

import java.util.Objects;

@Data(staticConstructor = "newInstance")
public class TransactionDtoConverter implements Converter<TransactionDto, Transaction> {

    @Override
    public Transaction convert(TransactionDto transactionDto) {
        if(Objects.isNull(transactionDto)){
            return null;
        }

        return Transaction.builder()
                .id(transactionDto.getId())
                .operation(transactionDto.getOperation())
                .amount(transactionDto.getAmount())
                .balance(transactionDto.getBalance())
                .date(transactionDto.getDate())
                .build();
    }
}
