package com.example.ribbonnativedemo.controller;

import com.example.ribbonnativedemo.interfaces.UserRemoteClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRemoteClient userRemoteClient;

    @ApiOperation(value = "FeignClient远程调用")
    @ApiResponses({ @ApiResponse(code = 200, message = "OK", response = String.class) })
    @GetMapping("/callHello")
    public String callHello() {
        //return restTemplate.getForObject("http://localhost:8083/house/hello",String.class);
        //String result = restTemplate.getForObject("http://eureka-client-user-service/user/hello",String.class);
        String result = userRemoteClient.hello();
        System.out.println("调用结果：" + result);
        return result;
    }
}