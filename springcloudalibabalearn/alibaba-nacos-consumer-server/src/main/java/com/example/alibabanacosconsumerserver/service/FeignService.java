package com.example.alibabanacosconsumerserver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider-service", fallback = FallbackServiceImpl.class)
//@FeignClient(value = "nacos-provider-service")
public interface FeignService {

    @GetMapping(value = "/test/{message}")
    String test(@PathVariable("message") String message);
}