package com.ymotse.booking.service;

import com.ymotse.booking.entity.Booking;
import com.ymotse.booking.proxy.ExchangeProxy;
import com.ymotse.booking.repository.BookingRepository;
import com.ymotse.booking.transfer.BookingRequest;
import com.ymotse.booking.transfer.BookingResponse;
import com.ymotse.booking.transfer.CurrencyType;
import com.ymotse.booking.transfer.ExchangeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepositoryMock;

    @Mock
    private ExchangeProxy exchangeProxy;

    @Test
    void listAll_returnsListOfBookings_whenSuccessful() {
        Booking booking01 = Booking.of("description 1", BigDecimal.valueOf(1.01), CurrencyType.BRL);
        ReflectionTestUtils.invokeSetterMethod(booking01, "id", 1);

        List bookings = new LinkedList();
        bookings.add(0, booking01);

        when(bookingRepositoryMock.findAll())
            .thenReturn(bookings);

        List<BookingResponse> result = bookingService.listAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(booking01.getId(), result.get(0).getId());
        assertEquals(booking01.getDescription(), result.get(0).getDescription());
        assertEquals(booking01.getValuePerDay(), result.get(0).getValue());
        assertEquals(booking01.getCurrency(), result.get(0).getExchange());
    }

    @Test
    void booking_returnsBookingResponse_whenSuccessful() {
        Booking booking = Booking.of("description 1", BigDecimal.valueOf(1.01), CurrencyType.USD);
        ReflectionTestUtils.invokeSetterMethod(booking, "id", 1);

        when(bookingRepositoryMock.findById(anyInt()))
                .thenReturn(Optional.of(booking));

        when(exchangeProxy.exchange(any(), any(),any()))
                .thenReturn(new ExchangeDto(BigDecimal.valueOf(1.01), booking.getCurrency(), CurrencyType.BRL, BigDecimal.TEN, "8080"));

        BookingResponse result = bookingService.booking(1, CurrencyType.BRL);

        assertNotNull(result);
        assertEquals(result.getId(), result.getId());
        assertEquals(result.getDescription(), result.getDescription());
        assertEquals(result.getValue(), result.getValue());
        assertEquals(result.getExchange(), result.getExchange());
    }

    @Test
    void create_returnsBookingResponse_whenSuccessful() {
        BookingRequest request = new BookingRequest();
        request.setDescription("Hotel ok");
        request.setExchange(CurrencyType.USD);
        request.setValue(BigDecimal.TEN);

        Booking bookingSaved = Booking.of(request.getDescription(), request.getValue(), request.getExchange());
        ReflectionTestUtils.invokeSetterMethod(bookingSaved, "id", 1);

        when(bookingRepositoryMock.save(any()))
                .thenReturn(bookingSaved);

        BookingResponse result = bookingService.create(request);

        assertNotNull(result);
        assertEquals(bookingSaved.getId(), result.getId());
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getValue(), result.getValue());
        assertEquals(request.getExchange(), result.getExchange());
    }
}