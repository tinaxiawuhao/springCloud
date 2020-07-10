package com.example.feigninheritapi.interfaces;

import com.example.feigninheritapi.entity.HouseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("feign-inherit-provide")
public interface UserRemoteClient {

    @GetMapping("/user/name")
    String getName();

    //多参数请求构造分为 GET 请求和 POST 请求两种方式，首先来看 GET 请求的多参数请求构造方式
    @GetMapping("/user/info")
    String getUserInfo(@RequestParam("name")String name, @RequestParam("age")int age);

    //另一种是通过 Map 来传递多个参数，参数数量可以动态改变，笔者在这里还是推荐大家用固定的参数方式，
    // 不要用 Map 来传递参数，Map 传递参数最大的问题是可以随意传参。代码如下所示
    @GetMapping("/user/detail")
    String getUserDetail(@RequestParam Map<String, Object> param);

//    POST 请求多参数就定义一个参数类，通过 @RequestBody 注解的方式来实现，代码如下所示。
    @PostMapping("/user/add")
    String addUser(@RequestBody HouseInfo houseInfo);
}