package com.ymotse.booking.service;

import com.ymotse.booking.entity.Booking;
import com.ymotse.booking.exception.NotFoundException;
import com.ymotse.booking.proxy.ExchangeProxy;
import com.ymotse.booking.repository.BookingRepository;
import com.ymotse.booking.transfer.BookingRequest;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import com.ymotse.booking.transfer.ExchangeDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ExchangeProxy exchangeProxy;

    public List<BookingResponse> listAll() {
        List<BookingResponse> bookings = new ArrayList<>();

        bookingRepository.findAll()
                .iterator()
                .forEachRemaining(booking -> bookings.add(BookingResponse.of(booking)));

        return bookings;
    }

    public Map<String, Object> findByDescriptionContainingIgnoreCase(String description, int page, int pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);

        Page<Booking> bookings = bookingRepository.findByDescriptionContainingIgnoreCase(description, paging);

        Map<String, Object> response = new HashMap<>();

        response.put("bookings", bookings.getContent());
        response.put("page", bookings.getNumber() + 1);
        response.put("items", bookings.getTotalElements());
        response.put("total-pages", bookings.getTotalPages());

        return response;
    }

    public BookingResponse booking(@NonNull Integer id, @NonNull CurrencyType currency) {
        Booking booking = bookingRepository.findById(id)
                                  .orElseThrow(() -> new NotFoundException(String.format("Booking with id %d not found.", id)));

        ExchangeDto exchange = exchangeProxy.exchange(booking.getValuePerDay(), booking.getCurrency(), currency);

        return BookingResponse.of(booking.getId(), booking.getDescription(), currency, exchange.getValue());
    }

    public BookingResponse create(@NonNull BookingRequest request) {
        Booking booking = Booking.of(request.getDescription(), request.getValue(), request.getExchange());

        Booking saved = bookingRepository.save(booking);

        return BookingResponse.of(saved.getId(), saved.getDescription(), saved.getCurrency(), saved.getValuePerDay());
    }

}
