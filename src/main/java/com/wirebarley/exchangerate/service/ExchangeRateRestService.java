package com.wirebarley.exchangerate.service;

import com.wirebarley.exchangerate.dto.ApiData;
import com.wirebarley.exchangerate.dto.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.Optional;

@Slf4j
@Service
public class ExchangeRateRestService {

    private final RestTemplate restTemplate;

    @Value("${access_key}")
    private String accessKey;

    @Value("${source}")
    private String source;

    // restTemplate 초기화
    public ExchangeRateRestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * 수취국가 선택시 환율 정보 조회
     *
     * @param currency
     * @return
     */
    public String getExchangeRateByCurrency(String currency) {
        double originExchangeRate = getExchangeRate(currency);
        return new DecimalFormat("###,###.00").format(originExchangeRate);
    }

    /**
     * 송금액 입력 시 수취금액 계산
     *
     * @param exchangeRateDTO
     * @return
     */
    public String getRemittanceAmount(ExchangeRate exchangeRateDTO) {
        return getDicimalFormatNumber(getReceivedAmount(exchangeRateDTO));
    }

    private double getExchangeRate(String currency) {
        ApiData apiData = restTemplate.getForObject("http://www.apilayer.net/api/live?access_key=" + accessKey + "&currencies=" + currency + "&source=" + source, ApiData.class);

        return Optional.ofNullable(apiData)
                .map(ApiData::getQuotes)
                .map(map -> map.get(source + currency))
                .orElseThrow(RuntimeException::new);
    }

    /**
     * 수취 금액 및 환율 포맷 설정
     *
     * @param number
     * @return
     */
    private String getDicimalFormatNumber(double number) {
        return new DecimalFormat("###,###.00").format(number);
    }

    /**
     * 환율과 송금액의 곱 계산
     *
     * @param exchangeRateDTO
     * @return
     */
    private double getReceivedAmount(ExchangeRate exchangeRateDTO){
        return getExchangeRate(exchangeRateDTO.getCurrency()) * exchangeRateDTO.getRemittanceAmount();
    }
}
