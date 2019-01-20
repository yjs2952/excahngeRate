package com.wirebarley.exchangerate.dto;

import lombok.*;


@Getter @Setter @ToString
public class ExchangeRate {
    private String remittanceCountry;   // 송금 국가
    private String currency;            // 수취 국가
    private double exchangeRate;        // 환율
    private int remittanceAmount;       // 송금액
}
