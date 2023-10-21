package com.ymotse.booking.repository;

import com.ymotse.booking.entity.Booking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("it")
@ExtendWith(SpringExtension.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(value = "/scripts/booking/bookingService-in.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/scripts/booking/bookingService-out.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookingRepositoryIT {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void listAll_returnsListOfBookings_whenSuccessful() {
        Iterable<Booking> iterable = bookingRepository.findAll();

        List<Booking> result = StreamSupport
                                        .stream(iterable.spliterator(), false)
                                        .collect(Collectors.toList());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(5, result.size());
    }
}