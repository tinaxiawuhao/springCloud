package com.example.ribbonnativedemo.config;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /*通常我们调用的接口都是有权限控制的，很多时候可能认证的值是通过参数去传递的，还有就是通过请求头去传递认证信息，
    比如 Basic 认证方式。在 Feign 中我们可以直接配置 Basic 认证，代码如下所示。*/
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password");
    }

    /*通过 Options 可以配置连接超时时间和读取超时时间（代码如下所示），Options 的第一个参数是连接超时时间（ms），
    默认值是 10×1000；第二个是取超时时间（ms），默认值是 60×1000。*/
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }
//    HttpClient 自动配置源码如下所示：
//    @Configuration
//    @ConditionalOnClass(ApacheHttpClient.class)
//    @ConditionalOnMissingClass("com.netflix.loadbalancer.ILoadBalancer")
//    @ConditionalOnProperty(value = "feign.httpclient.enabled", matchIfMissing = true)
//    protected static class HttpClientFeignConfiguration {
//        @Autowired(required = false)
//        private HttpClient httpClient;
//        @Bean
//        @ConditionalOnMissingBean(Client.class)
//        public Client feignClient() {
//            if (this.httpClient != null) {
//                return new ApacheHttpClient(this.httpClient);
//            }
//            return new ApacheHttpClient();
//        }
//    }

//    OkHttp 自动配置源码如下所示：
//        @Configuration
//        @ConditionalOnClass(OkHttpClient.class)
//        @ConditionalOnMissingClass("com.netflix.loadbalancer.ILoadBalancer")
//        @ConditionalOnProperty(value = "feign.okhttp.enabled", matchIfMissing = true)
//        protected static class OkHttpFeignConfiguration {
//            @Autowired(required = false)
//            private okhttp3.OkHttpClient okHttpClient;
//            @Bean
//            @ConditionalOnMissingBean(Client.class)
//            public Client feignClient() {
//                if (this.okHttpClient != null) {
//                    return new OkHttpClient(this.okHttpClient);
//                }
//                return new OkHttpClient();
//            }
//        }

    /*配置编码解码器只需要在 Feign 的配置类中注册 Decoder 和 Encoder 这两个类即可，代码如下所示。*/
//    @Bean
//    public Decoder decoder() {
//        return new MyDecoder();
//    }
//    @Bean
//    public Encoder encoder() {
//        return new MyEncoder();
//    }
}