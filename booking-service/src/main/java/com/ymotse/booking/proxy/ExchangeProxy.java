package com.ymotse.booking.proxy;

import com.ymotse.booking.transfer.CurrencyType;
import com.ymotse.booking.transfer.ExchangeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "exchange-service")
public interface ExchangeProxy {

    @GetMapping(value = "/exchange/{amount}/{from}/{to}")
    ExchangeDto exchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") CurrencyType from,
            @PathVariable("to") CurrencyType to
    );
}
