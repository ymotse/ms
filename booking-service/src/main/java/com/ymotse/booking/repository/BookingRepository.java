package com.ymotse.booking.repository;

import com.ymotse.booking.entity.Booking;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ymotse.booking.transfer.CurrencyType.*;

@Repository
public class BookingRepository {

    private final List<Booking> bookingList = Arrays.asList(
        Booking.of(1, "Hotel Xpto", BigDecimal.valueOf(34), USD),
        Booking.of(2, "Hotel Abc", BigDecimal.valueOf(89), BRL),
        Booking.of(3, "Hotel ZigZag", BigDecimal.valueOf(56), EUR),
        Booking.of(4, "Hotel Bibi", BigDecimal.valueOf(18), EUR),
        Booking.of(5, "Hotel Xiss", BigDecimal.valueOf(67), BRL),
        Booking.of(6, "Hotel Bae", BigDecimal.valueOf(40), USD),
        Booking.of(7, "Hotel Nuss", BigDecimal.valueOf(25), EUR),
        Booking.of(8, "Hotel Bats", BigDecimal.valueOf(67), EUR),
        Booking.of(9, "Hotel Wtf", BigDecimal.valueOf(103), BRL)
    );

    public Optional<Booking> getById(@NonNull Integer id) {
        return bookingList.stream()
                       .filter(booking -> booking.getId().equals(id))
                       .findFirst();
    }
}
