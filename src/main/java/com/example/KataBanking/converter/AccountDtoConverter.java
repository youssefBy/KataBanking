package com.example.KataBanking.converter;

import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.entity.Account;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Data(staticConstructor = "newInstance")
public class AccountDtoConverter implements Converter<AccountDto, Account> {

    @Override
    public Account convert(AccountDto accountDto) {
        if(Objects.isNull(accountDto)){
            return null;
        }

        return Account.builder()
                .id(accountDto.getId())
                .accountNumber(accountDto.getAccountNumber())
                .balance(accountDto.getBalance())
                .transactions(Objects.nonNull(accountDto.getTransactions()) ? accountDto.getTransactions().stream().map(TransactionDtoConverter.newInstance()::convert).collect(Collectors.toList()) : Arrays.asList())
                .build();
    }
}
