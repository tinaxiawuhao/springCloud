package com.example.ribbonnativedemo.config;

import com.example.ribbonnativedemo.interceptor.MyLoadBalanced;
import com.example.ribbonnativedemo.interceptor.MyLoadBalancerInterceptor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class MyLoadBalancerAutoConfiguration {

    @MyLoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();

    @Bean
    public MyLoadBalancerInterceptor myLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        return new MyLoadBalancerInterceptor(loadBalancerClient);
    }

    //维护一个restTemplates列表，在SmartInitializingSingleton 里对restTemplate进行拦截设置。
    @Bean
    public SmartInitializingSingleton myLoadBalancedRestTemplataInitializer(LoadBalancerClient loadBalancerClient){
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate restTemplate:MyLoadBalancerAutoConfiguration.this.restTemplates){
                    List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                    list.add(myLoadBalancerInterceptor(loadBalancerClient));
                    restTemplate.setInterceptors(list);
                }
            }
        };
    }


}