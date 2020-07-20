package com.example.hystrixfeigndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// 在启动类上添加 @EnableHystrix 或者 @EnableCircuitBreaker。注意，@EnableHystrix 中包含了 @EnableCircuitBreaker。
@EnableHystrix
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableFeignClients//(basePackages="com.example.hystrixfeigndemo.interfaces")
public class HystrixFeignDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignDemoApplication.class, args);
    }

}
