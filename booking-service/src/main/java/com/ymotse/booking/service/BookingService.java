package com.ymotse.booking.service;

import com.ymotse.booking.entity.Booking;
import com.ymotse.booking.proxy.ExchangeProxy;
import com.ymotse.booking.repository.BookingRepository;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import com.ymotse.booking.transfer.ExchangeDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final Environment environment;
    private final BookingRepository bookingRepository;
    private final ExchangeProxy exchangeProxy;

    public BookingResponse booking(@NonNull Integer id, @NonNull CurrencyType currency) {
        String port = environment.getProperty("local.server.port");

        Booking booking = bookingRepository.getById(id)
                                  .orElseThrow(IllegalArgumentException::new);

        ExchangeDto exchange = exchangeProxy.exchange(booking.getValuePerDay(), booking.getCurrencyDefault(), currency);

        return BookingResponse.builder()
                       .id(booking.getId())
                       .description(booking.getDescription())
                       .exchange(currency)
                       .value(exchange.getValue())
                       .environment(String.format("Booking Port: %s, Exchange Port: %s",  port, exchange.getEnvironment()))
                       .build();
    }
}
