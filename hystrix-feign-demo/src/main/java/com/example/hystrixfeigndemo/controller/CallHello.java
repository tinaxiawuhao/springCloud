package com.example.hystrixfeigndemo.controller;

import com.example.hystrixfeigndemo.interfaces.UserRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class CallHello {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultCallHello",commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy", value = "THREAD")
    }
    )
    @GetMapping("/callHello")
    @ResponseBody
    public String callHello() {
        String result = restTemplate.getForObject("http://localhost:8088/user/hello", String.class);
        return result;
    }

    public String defaultCallHello() {
        return "fail...1";
    }


    @GetMapping("/callHello2")
    @ResponseBody
    public String callHello2() {
        String hello = userRemoteClient.hello();
        System.out.println("调用结果：" + hello);
        return hello;
    }
}
