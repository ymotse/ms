package com.ymotse.booking.service;

import com.ymotse.booking.entity.Booking;
import com.ymotse.booking.proxy.ExchangeProxy;
import com.ymotse.booking.repository.BookingRepository;
import com.ymotse.booking.transfer.BookingRequest;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import com.ymotse.booking.transfer.ExchangeDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ExchangeProxy exchangeProxy;

    public List<BookingResponse> listAll() {
        List<BookingResponse> bookings = new ArrayList<>();

        bookingRepository.findAll()
                .iterator()
                .forEachRemaining(booking -> {
                    BookingResponse bookingResponse = BookingResponse.of(booking.getId(), booking.getDescription(), booking.getCurrency(), booking.getValuePerDay());

                    bookings.add(bookingResponse);
                });

        return bookings;
    }

    public BookingResponse booking(@NonNull Integer id, @NonNull CurrencyType currency) {
        Booking booking = bookingRepository.findById(id)
                                  .orElseThrow(IllegalArgumentException::new);

        ExchangeDto exchange = exchangeProxy.exchange(booking.getValuePerDay(), booking.getCurrency(), currency);

        return BookingResponse.of(booking.getId(), booking.getDescription(), currency, exchange.getValue());
    }

    public BookingResponse create(@NonNull BookingRequest request) {
        Booking booking = Booking.of(request.getDescription(), request.getValue(), request.getExchange());

        Booking saved = bookingRepository.save(booking);

        return BookingResponse.of(saved.getId(), saved.getDescription(), saved.getCurrency(), saved.getValuePerDay());
    }

}
