package com.example.ribbonnativedemo.config;

import com.example.ribbonnativedemo.interceptor.MyLoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
//    @LoadBalanced
    @MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}