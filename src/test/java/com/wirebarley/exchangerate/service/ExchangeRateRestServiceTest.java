package com.wirebarley.exchangerate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRateRestServiceTest {

    @Autowired
    private ExchangeRateRestService exchangeRateRestService;

    @Test
    public void  외부_API_호출_테스트(){
        exchangeRateRestService.method();
    }
}
