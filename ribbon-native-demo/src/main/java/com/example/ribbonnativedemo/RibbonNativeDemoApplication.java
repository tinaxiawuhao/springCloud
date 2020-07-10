package com.example.ribbonnativedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//在启动类上加 @EnableFeignClients 注解，如果你的 Feign 接口定义跟你的启动类不在同一个包名下，
// 还需要制定扫描的包名 @EnableFeignClients（basePackages=“com.fangjia.api.client”），
@EnableFeignClients
public class RibbonNativeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonNativeDemoApplication.class, args);
    }

}
