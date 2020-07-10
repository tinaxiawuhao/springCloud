package com.example.ribbonnativedemo.interfaces;

import com.example.ribbonnativedemo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//配置类建好后，我们需要在 Feign Client 中的 @FeignClient 注解中指定使用的配置类，代码如下所示。
@FeignClient(value = "eureka-client-user-service", configuration = FeignConfiguration.class)
public interface UserRemoteClient {

    @GetMapping("/user/hello")
    String hello();
}
