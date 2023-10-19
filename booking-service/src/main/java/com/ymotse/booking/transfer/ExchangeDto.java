package com.ymotse.booking.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ExchangeDto {
    private BigDecimal amount;
    private CurrencyType from;
    private CurrencyType to;
    private BigDecimal value;
    private String environment;
}
