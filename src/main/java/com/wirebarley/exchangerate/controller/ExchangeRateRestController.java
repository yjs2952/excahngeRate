package com.wirebarley.exchangerate.controller;

import com.wirebarley.exchangerate.dto.ExchangeRate;
import com.wirebarley.exchangerate.service.ExchangeRateRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/exchangeRates")
public class ExchangeRateRestController {

    private ExchangeRateRestService exchangeRateRestService;

    public ExchangeRateRestController(ExchangeRateRestService exchangeRateRestService) {
        this.exchangeRateRestService = exchangeRateRestService;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<?> getExchangeRate(@PathVariable String currency) {
        try {
            return ResponseEntity.ok(exchangeRateRestService.getExchangeRateByCurrency(currency));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
