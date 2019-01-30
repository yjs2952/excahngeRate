package com.wirebarley.exchangerate.dto;

import lombok.Data;

import java.util.Map;

/**
 * TODO : 필요 없는 필드는 제거하는 게 좋다.
 */
@Data
public class ApiData {
    private boolean success;
    private String terms;
    private String privacy;
    private String timestamp;
    private String source;
    private Map<String, Double> quotes;
}
