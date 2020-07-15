package com.example.hystrixfeigndemo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class CallHello {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/callHello")
    @HystrixCommand(fallbackMethod = "defaultCallHello")
    @ResponseBody
    public String callHello() {
        String result = restTemplate.getForObject("http://localhost:8088/house/hello", String.class);
        return result;
    }

    public String defaultCallHello() {
        return "fail...";
    }
}
