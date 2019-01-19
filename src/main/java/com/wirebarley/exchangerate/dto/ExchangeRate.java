package com.wirebarley.exchangerate.dto;

import lombok.*;


@Builder
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class ExchangeRate {
    private String remittanceCountry;   // 송금 국가
    private String receptionCountry;    // 수취 국가
    private long exchangeRate;          // 환율
    private int remittanceAmount;      // 송금액
}
