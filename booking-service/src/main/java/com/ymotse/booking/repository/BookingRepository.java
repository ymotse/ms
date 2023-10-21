package com.ymotse.booking.repository;

import com.ymotse.booking.entity.Booking;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Optional<Booking> findById(@NonNull Integer id);
}
