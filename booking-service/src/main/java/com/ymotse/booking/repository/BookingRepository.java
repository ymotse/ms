package com.ymotse.booking.repository;

import com.ymotse.booking.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

    Optional<Booking> findById(Integer id);

    Page<Booking> findByDescriptionContainingIgnoreCase(String description, Pageable pageable);
}
