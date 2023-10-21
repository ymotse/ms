package com.ymotse.booking.transfer;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class BookingRequest {

    @NotBlank(message = "The 'description' field cannot be empty.")
    @Length(max = 100, message = "The 'description' field max length is 100 chars.")
    private String description;

    @NotNull(message = "The 'exchange' field cannot be null or invalid value.")
    private CurrencyType exchange;

    @NotNull(message = "The 'Value' field cannot be null or invalid value.")
    private BigDecimal value;
}
