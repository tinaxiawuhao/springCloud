package com.example.eurekaclientuserservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test/")
public class UserController {

    @GetMapping("user/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}