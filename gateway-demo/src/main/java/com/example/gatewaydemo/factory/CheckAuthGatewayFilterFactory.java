package com.example.gatewaydemo.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/*如果你的配置是 Key、Value 这种形式的，那么可以不用自己定义配置类，直接继承 AbstractNameValueGatewayFilterFactory 类即可。
AbstractNameValueGatewayFilterFactory 类继承了 AbstractGatewayFilterFactory，定义了一个 NameValueConfig 配置类，NameValueConfig 中有 name 和 value 两个字段。
我们可以直接使用，AddRequestHeaderGatewayFilterFactory、AddRequestParameterGatewayFilterFactory 等都是直接继承的 AbstractNameValueGatewayFilterFactory。
继承 AbstractNameValueGatewayFilterFactory 方式定义过滤器工厂，代码如下所示*/
/*
* filters：
        - CheckAuth=zhangsan,男
        * */
@Component
public class CheckAuthGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            System.err.println("进入了CheckAuthGatewayFilterFactory:" + config.getName() + "\t" + config.getValue());
            ServerHttpRequest request = exchange.getRequest().mutate().build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}