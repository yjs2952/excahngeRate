package com.wirebarley.exchangerate.service;

import com.wirebarley.exchangerate.dto.ApiData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateRestServiceTest {

    @Autowired
    private ExchangeRateRestService exchangeRateRestService;

    @Test
    public void  외부_API_호출_테스트(){
        log.info(new DecimalFormat("###,###.##").format(100000000000D));
        Assert.assertEquals("1125.803792", exchangeRateRestService.getExchangeRateByCurrency("KRW"));

    }
}
