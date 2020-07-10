package com.example.ribbonnativedemo.config;

import com.example.ribbonnativedemo.interceptor.MyLoadBalanced;
import com.example.ribbonnativedemo.rule.MyRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    /**
    ribbon负载均衡*/
    @Bean
//    @LoadBalanced
    @MyLoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 负载均衡策略
     * @return
     */
    @Bean
    public MyRule rule() {
        return new MyRule();
    }
}