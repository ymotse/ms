package com.ymotse.exchange.business;

import com.ymotse.exchange.transfer.CurrencyType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Qualifier("currencyMultiplierEUR")
public class CurrencyIdentifierEUR implements CurrencyIdentifier {

    @Override
    public BigDecimal getMultiplier(CurrencyType to) {
        return switch (to) {
            case USD ->
                    BigDecimal.valueOf(1.0561);
            case BRL ->
                    BigDecimal.valueOf(5.31862);
            case EUR ->
                    BigDecimal.ONE;
        };
    }
}
