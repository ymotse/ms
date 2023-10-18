package com.ymotse.exchange.business;

import com.ymotse.exchange.transfer.CurrencyType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("currencyMultiplierUSD")
public class CurrencyIdentifierUSD implements CurrencyIdentifier {

    @Override
    public BigDecimal getMultiplier(CurrencyType to) {
        return switch (to) {
            case EUR ->
                    BigDecimal.valueOf(0.94674);
            case BRL ->
                    BigDecimal.valueOf(5.03611);
            default ->
                    throw new IllegalArgumentException();
        };
    }
}
