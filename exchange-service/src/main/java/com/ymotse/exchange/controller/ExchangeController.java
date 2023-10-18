package com.ymotse.exchange.controller;

import com.ymotse.exchange.entity.Exchange;
import com.ymotse.exchange.service.ExchangeService;
import com.ymotse.exchange.transfer.CurrencyType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Exchange exchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") CurrencyType from,
            @PathVariable("to") CurrencyType to
    ) {
        return exchangeService.convert(amount, from, to);
    }
}
