package com.wirebarley.exchangerate.controller;

import com.wirebarley.exchangerate.dto.ExchangeRate;
import com.wirebarley.exchangerate.service.ExchangeRateRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    /*@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String badRequest() {
        return "잘못된 요청입니다.";
    }*/

    @PostMapping
    public ResponseEntity<?> getRemittanceAmount(@RequestBody @Valid ExchangeRate exchangeRateDTO, BindingResult bindingResult) {

        // validation 체크
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            return ResponseEntity.ok(exchangeRateRestService.getRemittanceAmount(exchangeRateDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
