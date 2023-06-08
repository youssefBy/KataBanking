package com.example.KataBanking.service.design_patterns.observer;

import java.math.BigDecimal;

public interface AccountObserver {
    void update(BigDecimal newBalance);
}
