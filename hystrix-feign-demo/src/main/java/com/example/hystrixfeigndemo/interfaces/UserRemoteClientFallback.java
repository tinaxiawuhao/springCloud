package com.example.hystrixfeigndemo.interfaces;

import org.springframework.stereotype.Component;

@Component
public class UserRemoteClientFallback implements UserRemoteClient {
    @Override
    public String hello() {
        return "fail...2";
    }
}