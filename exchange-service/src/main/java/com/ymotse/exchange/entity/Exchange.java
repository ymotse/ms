package com.ymotse.exchange.entity;

import com.ymotse.exchange.transfer.CurrencyType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Exchange {
    private BigDecimal amount;
    private CurrencyType from;
    private CurrencyType to;
    private BigDecimal value;
    private String environment;

    public static Exchange of(BigDecimal amount, CurrencyType from, CurrencyType to, BigDecimal value, String environment) {
        Exchange exchange = new Exchange();
        exchange.amount = amount;
        exchange.from = from;
        exchange.to = to;
        exchange.value = value;
        exchange.environment = environment;

        return exchange;
    }
}
