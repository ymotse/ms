package com.ymotse.booking.controller;

import com.ymotse.booking.service.BookingService;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(value = "/{id}/{currency}")
    public BookingResponse booking(@PathVariable("id") Integer id, @PathVariable("currency") CurrencyType currency) {
        return bookingService.booking(id, currency);
    }
}
