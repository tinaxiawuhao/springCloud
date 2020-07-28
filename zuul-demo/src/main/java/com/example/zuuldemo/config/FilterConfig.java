package com.example.zuuldemo.config;

import com.example.zuuldemo.filter.ErrorFilter;
import com.example.zuuldemo.filter.IpFilter;
import com.example.zuuldemo.filter.PostFilter;
import com.example.zuuldemo.filter.TestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public IpFilter ipFilter() {
        return new IpFilter();
    }

    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
}