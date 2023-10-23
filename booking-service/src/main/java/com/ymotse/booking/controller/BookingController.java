package com.ymotse.booking.controller;

import com.ymotse.booking.service.BookingService;
import com.ymotse.booking.transfer.BookingRequest;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/all")
    public List<BookingResponse> listAll() {
        return bookingService.listAll();
    }

    @GetMapping
    public Map<String, Object> findByDescriptionContainingIgnoreCase(@RequestParam String description, @RequestParam int page, @RequestParam("page-size") int pageSize) {
        return bookingService.findByDescriptionContainingIgnoreCase(description, page, pageSize);
    }

    @GetMapping(value = "/{id}/{currency}")
    public BookingResponse booking(@PathVariable("id") Integer id, @PathVariable("currency") CurrencyType currency) {
        return bookingService.booking(id, currency);
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.of(Optional.of(bookingService.create(request)));
    }
}
