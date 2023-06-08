package com.example.KataBanking.service.desing_patterns;

import com.example.KataBanking.service.design_patterns.adapter.LegacyAccount;
import com.example.KataBanking.service.design_patterns.adapter.LegacyAccountAdapter;
import com.example.KataBanking.service.design_patterns.builder.AccountBuilder;
import com.example.KataBanking.service.design_patterns.decorator.AccountWithLimitDecorator;
import com.example.KataBanking.service.design_patterns.decorator.IAccount;
import com.example.KataBanking.service.design_patterns.decorator.InterestAccountDecorator;
import com.example.KataBanking.service.design_patterns.observer.BalanceLogger;
import com.example.KataBanking.service.design_patterns.singleton.Account;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class DesignPatternsTests {

    @Test
    public void testSingleton() {
        Account account = Account.getInstance();

        account.setAccountNumber("1");

        Account account1 = Account.getInstance();

        System.out.println(account1.getAccountNumber());

    }

    @Test
    public void testBuilder() {
        com.example.KataBanking.service.design_patterns.builder.Account account = new AccountBuilder()
                .accounNumber("123")
                .balance(new BigDecimal(0))
                .build();

        System.out.println(account);
    }

    @Test
    public void testPrototype() throws CloneNotSupportedException {
        com.example.KataBanking.service.design_patterns.prototype.Account account = new com.example.KataBanking.service.design_patterns.prototype.Account("1",new BigDecimal(0));


        com.example.KataBanking.service.design_patterns.prototype.Account clonedAccount;

        clonedAccount = account.clone();

        clonedAccount.setAccountNumber("2");
        clonedAccount.setBalance(new BigDecimal(0));

        System.out.println(account);
        System.out.println(clonedAccount);
    }

    @Test
    public void testAdapter() {
        com.example.KataBanking.service.design_patterns.adapter.Account account = new LegacyAccountAdapter(new LegacyAccount());

        account.deposit(new BigDecimal("1000.00"));
        account.withdraw(new BigDecimal("500.00"));
        BigDecimal balance = account.getBalance();

        System.out.println(balance);
    }

    @Test
    public void testObserver() {
        com.example.KataBanking.service.design_patterns.observer.Account account = new com.example.KataBanking.service.design_patterns.observer.Account("1", BigDecimal.ZERO);
        BalanceLogger balanceLogger = new BalanceLogger();

        account.addObserver(balanceLogger);

        account.deposit(new BigDecimal(1000));

        account.withdraw(new BigDecimal(500));
    }

    @Test
    public void testDecorator() {
        IAccount account = new com.example.KataBanking.service.design_patterns.decorator.Account(BigDecimal.valueOf(1000));
        account = new AccountWithLimitDecorator(account, BigDecimal.valueOf(500));
        account = new InterestAccountDecorator(account, BigDecimal.valueOf(0.05));

        account.deposit(BigDecimal.valueOf(500));
        account.withdraw(BigDecimal.valueOf(2000));
        System.out.println(account.getBalance());
    }
}
