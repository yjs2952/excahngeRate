package com.wirebarley.exchangerate.controller;

import com.wirebarley.exchangerate.dto.ExchangeRate;
import com.wirebarley.exchangerate.service.ExchangeRateService;
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

    private ExchangeRateService exchangeRateService;

    public ExchangeRateRestController(ExchangeRateService exchangeRateService) { // 서비스 생성자 주입
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * 특정 국가 환율 조회
     *
     * @param currency
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getExchangeRate(@RequestParam String currency) {
        try {
            return ResponseEntity.ok(exchangeRateService.getExchangeRateByCurrency(currency));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 수취금액 계산
     *
     * @param exchangeRateDTO
     * @param bindingResult
     * @return
     */
    @GetMapping("/receivedAmount")
    public ResponseEntity<?> getRemittanceAmount(@ModelAttribute @Valid ExchangeRate exchangeRateDTO, BindingResult bindingResult) {

        // validation 체크
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            return ResponseEntity.ok(exchangeRateService.getRemittanceAmount(exchangeRateDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
