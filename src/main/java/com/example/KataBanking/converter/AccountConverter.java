package com.example.KataBanking.converter;

import com.example.KataBanking.model.dto.AccountDto;
import com.example.KataBanking.model.entity.Account;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Data(staticConstructor = "newInstance")
public class AccountConverter implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account account) {
        if(Objects.isNull(account)){
            return null;
        }

        return AccountDto.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .balance(account.getBalance())
                .transactions(Objects.nonNull(account.getTransactions()) ? account.getTransactions().stream().map(TransactionConverter.newInstance()::convert).collect(Collectors.toList()) : Arrays.asList())
                .build();
    }
}
