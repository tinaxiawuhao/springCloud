package com.example.hystrixfeigndemo.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignConfiguration {
    /**
     * 日志级别
     * @return
     * 通过源码可以看到日志等级有 4 种，分别是：
     * NONE：不输出日志。
     * BASIC：只输出请求方法的 URL 和响应的状态码以及接口执行的时间。
     * HEADERS：将 BASIC 信息和请求头信息输出。
     * FULL：输出完整的请求信息。
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    /*通过 Options 可以配置连接超时时间和读取超时时间（代码如下所示），Options 的第一个参数是连接超时时间（ms），
    默认值是 10×1000；第二个是取超时时间（ms），默认值是 60×1000。*/
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    /*另一种是通过代码的方式禁用某个客户端，在 Feign 的配置类中增加如下所示的代码。*/
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}