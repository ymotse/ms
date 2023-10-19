package com.ymotse.booking.entity;

import com.ymotse.booking.transfer.CurrencyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Booking {
    private Integer id;
    private String description;
    private BigDecimal valuePerDay;
    private CurrencyType currencyDefault;

    public static Booking of(Integer id, String description, BigDecimal value, CurrencyType currencyDefault) {
        Booking booking = new Booking(id, description, value, currencyDefault);
        booking.id = id;
        booking.description = description;
        booking.valuePerDay = value;
        booking.currencyDefault = currencyDefault;

        return booking;
    }
}
