package com.wirebarley.exchangerate.controller;

import com.wirebarley.exchangerate.dto.ExchangeRate;
import com.wirebarley.exchangerate.service.ExchangeRateRestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  TODO : 예외 처리 할 때 bad_request 만 나오기 때문에 서버단의 오류인 경우 500 에러가 나와야 하는 것 처럼 예외를 분류해서 한번에 처리하는 것이 좋다.
 *  TODO : 예외 처리 모듈을 따로 만들어서 어떤 오류인지 판단하고 그에 맞게 리턴하도록 하게 하는 것이 좋다.
 */
@Slf4j
@RestController
@RequestMapping("/api/exchangeRates")
public class ExchangeRateRestController {

    private ExchangeRateRestService exchangeRateRestService;

    public ExchangeRateRestController(ExchangeRateRestService exchangeRateRestService) { // 서비스 생성자 주입
        this.exchangeRateRestService = exchangeRateRestService;
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
            return ResponseEntity.ok(exchangeRateRestService.getExchangeRateByCurrency(currency));
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
            return ResponseEntity.ok(exchangeRateRestService.getRemittanceAmount(exchangeRateDTO));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
