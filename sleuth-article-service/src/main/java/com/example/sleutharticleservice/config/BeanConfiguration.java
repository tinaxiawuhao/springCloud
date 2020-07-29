package com.example.sleutharticleservice.config;

import brave.http.HttpAdapter;
import brave.http.HttpSampler;
import org.springframework.cloud.sleuth.instrument.web.ServerSampler;
import org.springframework.cloud.sleuth.instrument.web.SkipPatternProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    过滤不想跟踪的请求
//对于某些请求不想开启跟踪，可以通过配置 HttpSampler 来过滤掉，比如 swagger 这些请求等。代码如下所示。
//核心在 trySample 方法中，只要不想跟踪的 URL 直接返回 false 就可以过滤。规则可以自定，笔者用了 SkipPatternProvider 来过滤，
// SkipPatternProvider 中的 skipPattern 配置了很多过滤规则。
/*/api-docs.*|/autoconfig|/configprops|/dump|/health|/info|/metrics.*|
/mappings|/trace|/swagger.*|.*\.png|.*\.css|.*\.js|.*\.html|/favicon.ico|
/hystrix.stream|/application/.*|/actuator.*|/cloudfoundryapplication*/
    @Bean(name = ServerSampler.NAME)
    HttpSampler myHttpSampler(SkipPatternProvider provider) {
        Pattern pattern = provider.skipPattern();
        return new HttpSampler() {
            @Override
            public <Req> Boolean trySample(HttpAdapter<Req, ?> adapter, Req request) {
                String url = adapter.path(request);
                boolean shouldSkip = pattern.matcher(url).matches();
                if (shouldSkip) {
                    return false;
                }
                return null;
            }
        };
    }
}