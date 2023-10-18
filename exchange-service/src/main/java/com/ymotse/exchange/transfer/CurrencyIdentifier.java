package com.ymotse.exchange.transfer;

import java.math.BigDecimal;

public interface CurrencyIdentifier {

    BigDecimal getMultiplier(CurrencyType to);
}
