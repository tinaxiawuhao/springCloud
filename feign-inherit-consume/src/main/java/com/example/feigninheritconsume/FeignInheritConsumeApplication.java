package com.example.feigninheritconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//在启动类上加 @EnableFeignClients 注解，如果你的 Feign 接口定义跟你的启动类不在同一个包名下，
// 还需要制定扫描的包名 @EnableFeignClients（basePackages=“com.fangjia.api.client”），
@EnableFeignClients(basePackages="com.example.feigninheritapi.interfaces")
//从Spring Cloud Edgware开始，@EnableDiscoveryClient 或@EnableEurekaClient
// 可省略。只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上。
@EnableDiscoveryClient
public class FeignInheritConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignInheritConsumeApplication.class, args);
    }

}
