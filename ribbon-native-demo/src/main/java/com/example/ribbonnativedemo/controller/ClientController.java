package com.example.ribbonnativedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancer;

    @GetMapping("/choose")
    public Object chooseUrl() {
        ServiceInstance instance = loadBalancer.choose("ribbon-eureka-demo");
        return instance;
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) throws Exception {
        System.err.println(request.getHeader("X-Request-Foo"));
        return "success";
    }
}
