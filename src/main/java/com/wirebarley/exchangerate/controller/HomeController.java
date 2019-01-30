package com.wirebarley.exchangerate.controller;

import com.wirebarley.exchangerate.service.ExchangeRateRestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    private ExchangeRateRestService exchangeRateRestService;

    HomeController(ExchangeRateRestService exchangeRateRestService) {
        this.exchangeRateRestService = exchangeRateRestService;
    }

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("exchangeRate", exchangeRateRestService.getExchangeRateByCurrency("KRW"));
        return "index";
    }
}
