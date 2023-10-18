package com.ymotse.exchange.service;

import com.ymotse.exchange.entity.Exchange;
import com.ymotse.exchange.transfer.CurrencyIdentifier;
import com.ymotse.exchange.transfer.CurrencyType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    private final Environment environment;

    @Autowired
    @Qualifier("currencyMultiplierBRL")
    private CurrencyIdentifier currencyIdentifierBRL;

    @Autowired
    @Qualifier("currencyIdentifierUSD")
    private CurrencyIdentifier currencyIdentifierUSD;

    @Autowired
    @Qualifier("currencyMultiplierEUR")
    private CurrencyIdentifier currencyMultiplierEUR;

    public Exchange convert(@NonNull BigDecimal amount, @NonNull CurrencyType from, @NonNull CurrencyType to) {
        BigDecimal multiplier = getMultiplier(from, to);

        BigDecimal value = multiplier.multiply(amount);

        return Exchange.of(amount, from, to, value.setScale(2, RoundingMode.UP), environment.getProperty("local.server.port"));
    }

    BigDecimal getMultiplier(@NonNull CurrencyType from, @NonNull CurrencyType to) {
        return switch (from) {
            case BRL ->
                    currencyIdentifierBRL.getMultiplier(to);
            case USD ->
                    currencyIdentifierUSD.getMultiplier(to);
            case EUR ->
                    currencyMultiplierEUR.getMultiplier(to);
        };
    }
}
