package com.example.feigninheritprovide.controller;

import com.example.feigninheritapi.entity.HouseInfo;
import com.example.feigninheritapi.interfaces.UserRemoteClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController implements UserRemoteClient {

    @Override
    public String getName() {
        System.out.println("我给了服务了");
        return "zhangsan";
    }

    @Override
    public String getUserInfo(String name, int age) {
        return null;
    }

    @Override
    public String getUserDetail(Map<String, Object> param) {
        return null;
    }

    @Override
    public String addUser(@RequestBody HouseInfo houseInfo) {
        return houseInfo.getName();
    }
}