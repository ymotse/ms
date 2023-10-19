package com.ymotse.booking.transfer;

import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
public class BookingResponse {
    private Integer id;
    private String description;
    private CurrencyType exchange;
    private BigDecimal value;
    private String environment;
}
