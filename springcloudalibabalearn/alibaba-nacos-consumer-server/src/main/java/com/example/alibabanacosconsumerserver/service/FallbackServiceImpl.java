package com.example.alibabanacosconsumerserver.service;

import org.springframework.stereotype.Component;

@Component
public class FallbackServiceImpl implements FeignService {

    @Override
    public String test(String message) {
        return "test fallback";
    }
}