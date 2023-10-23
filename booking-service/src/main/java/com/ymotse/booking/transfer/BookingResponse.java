package com.ymotse.booking.transfer;

import com.ymotse.booking.entity.Booking;
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

    public static BookingResponse of(Booking booking) {
        return BookingResponse.of(booking.getId(), booking.getDescription(), booking.getCurrency(), booking.getValuePerDay());
    }
}
