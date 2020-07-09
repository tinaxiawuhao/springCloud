package com.example.eurekaclientuserservice.controller;

import com.example.eurekaclientuserservice.entity.HouseInfo;
import org.springframework.web.bind.annotation.*;

@RestController
public class HouseController {
    @GetMapping("/house/data")
    public HouseInfo getData(@RequestParam("name") String name) {
        System.out.println("======"+name+"=======");
        return new HouseInfo(1L, "上海", "虹口","张三");
    }

    @GetMapping("/house/data/{name}")
    public String getData2(@PathVariable("name") String name) {
        System.out.println("======"+name+"=======");
        return name;
    }

    @PostMapping("/house/save")
    public Long addData(@RequestBody HouseInfo houseInfo) {
        System.out.println("======"+houseInfo.getName()+"=======");
        return 1001L;
    }
}
