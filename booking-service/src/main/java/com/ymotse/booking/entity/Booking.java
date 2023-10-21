package com.ymotse.booking.entity;

import com.ymotse.booking.transfer.CurrencyType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "booking")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = { "id" })
public class Booking {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_booking_id")
    @SequenceGenerator(name = "seq_booking_id", sequenceName = "seq_booking_id", allocationSize = 1)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "value_per_day")
    private BigDecimal valuePerDay;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    public static Booking of(String description, BigDecimal value, CurrencyType currency) {
        Booking booking = new Booking();
        booking.description = description;
        booking.valuePerDay = value;
        booking.currency = currency;

        return booking;
    }
}
