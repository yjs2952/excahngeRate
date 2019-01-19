package com.wirebarley.exchangerate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@Service
public class ExchangeRateRestService {
    private final RestTemplate restTemplate;

    /*@Autowired
    private Environment env;*/

    @Value("${access_key}")
    private String accessKey;

    @Value("${currencies}")
    private String currencies;

    @Value("${source}")
    private String source;

    public ExchangeRateRestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("http://www.apilayer.net/api/live?access_key=" + accessKey + "&currencies=" + currencies + "&source=" + source).build();
        //this.restTemplate = restTemplateBuilder.rootUri("http://www.apilayer.net/api/live?access_key=" + env.getProperty("access_key") + "&currencies=" + env.getProperty("currencies") + "&source=" + env.getProperty("source")).build();
    }

    public void method() {
        log.info(restTemplate.getForObject("http://www.apilayer.net/api/live?access_key=" + accessKey + "&currencies=" + currencies + "&source=" + source, HashMap.class).toString());
    }

}
