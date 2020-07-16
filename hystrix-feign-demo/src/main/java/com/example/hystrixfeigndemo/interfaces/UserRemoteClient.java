package com.example.hystrixfeigndemo.interfaces;

import com.example.hystrixfeigndemo.config.FeignConfiguration;
import com.example.hystrixfeigndemo.factory.UserRemoteClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient (value = "eureka-client-user-service", fallback = UserRemoteClientFallback.class)
@FeignClient(value = "eureka-client-user-service", configuration = FeignConfiguration.class, fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello();
}