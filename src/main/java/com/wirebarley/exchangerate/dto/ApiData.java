package com.wirebarley.exchangerate.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ApiData {
    private boolean success;
    private String terms;
    private String privacy;
    private String timestamp;
    private String source;
    private Map<String, Double> quotes;
}
