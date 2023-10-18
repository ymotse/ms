package com.ymotse.exchange.business;

import com.ymotse.exchange.transfer.CurrencyType;

import java.math.BigDecimal;

public interface CurrencyIdentifier {

    BigDecimal getMultiplier(CurrencyType to);
}
