package com.example.KataBanking.service.desing_patterns;

import com.example.KataBanking.service.design_patterns.factory.Account;
import com.example.KataBanking.service.design_patterns.factory.BasicAccountFactory;
import com.example.KataBanking.service.design_patterns.factory.InterestAccountFactory;
import org.junit.jupiter.api.Test;

public class FactoryTests {

    @Test
    public void testFactory() {
        BasicAccountFactory basicAccountFactory = new BasicAccountFactory();
        InterestAccountFactory interestAccountFactory = new InterestAccountFactory();

        Account basicAccount = basicAccountFactory.createAccount("1");
        Account interestsAccount = interestAccountFactory.createAccount("2");


        System.out.println(basicAccount.getAccountType());

        System.out.println(interestsAccount.getAccountType());

    }
}
