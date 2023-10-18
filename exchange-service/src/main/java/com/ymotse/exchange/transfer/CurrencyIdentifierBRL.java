package com.ymotse.exchange.transfer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("currencyMultiplierBRL")
public class CurrencyIdentifierBRL implements CurrencyIdentifier {

    @Override
    public BigDecimal getMultiplier(CurrencyType to) {
        return switch (to) {
            case USD ->
                    BigDecimal.valueOf(0.19843);
            case EUR ->
                    BigDecimal.valueOf(0.18787);
            default ->
                    throw new IllegalArgumentException();
        };
    }
}
