package com.example.hystrixfeigndemo.factory;

import com.example.hystrixfeigndemo.interfaces.UserRemoteClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
/*过 fallback 已经可以实现服务不可用时回退的功能，如果你想知道触发回退的原因，可以使用 FallbackFactory 来实现回退功能，代码如下所示。
* 笔者在这个回退处理的时候，将异常信息通过日志输出了，我们重新调用接口，可以看到异常信息在开发工具的控制台中输出了，FallbackFactory 和 Fallback 唯一的区别就在这里。*/
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