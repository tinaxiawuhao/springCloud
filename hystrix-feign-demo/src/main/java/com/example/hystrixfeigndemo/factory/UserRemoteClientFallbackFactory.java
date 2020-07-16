package com.example.hystrixfeigndemo.factory;

import com.example.hystrixfeigndemo.interfaces.UserRemoteClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserRemoteClientFallbackFactory implements FallbackFactory<UserRemoteClient> {
    private Logger logger = LoggerFactory.getLogger(UserRemoteClientFallbackFactory.class);
    @Override
    public UserRemoteClient create(final Throwable cause) {
        logger.error("UserRemoteClient回退：", cause);
        return new UserRemoteClient() {
            @Override
            public String hello() {
                return "fail...3";
            }
        };
    }
}