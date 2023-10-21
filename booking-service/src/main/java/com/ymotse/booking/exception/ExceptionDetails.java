package com.ymotse.booking.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionDetails {
    private String title;
    private String message;
    private String details;
    private LocalDateTime stamp;
}
