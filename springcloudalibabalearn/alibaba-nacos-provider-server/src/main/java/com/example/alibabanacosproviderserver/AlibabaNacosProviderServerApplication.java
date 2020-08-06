package com.example.alibabanacosproviderserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @EnableDiscoveryClient 开启服务注册发现功能
 */
@EnableDiscoveryClient
@SpringBootApplication

public class AlibabaNacosProviderServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosProviderServerApplication.class, args);
    }
}
