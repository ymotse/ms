package com.ymotse.booking.transfer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookingResponse {
    private Integer id;
    private String description;
    private CurrencyType exchange;
    private BigDecimal value;

    public static BookingResponse of(Integer id, String description, CurrencyType currency, BigDecimal value) {
        return new BookingResponse(id, description, currency, value);
    }
}
