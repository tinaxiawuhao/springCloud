package com.example.sleutharticleservice.controller;

import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    Tracer tracer;

    @GetMapping("/article/callHello")
    @NewSpan(name = "saveLog2")
    public String callHello() {
        logger.info("我是/article/callHello");
        tracer.currentSpan().tag("用户", "zhangsan");
        return restTemplate.getForObject("http://localhost:8093/sleuth-user-service/user/hello", String.class);
    }

}