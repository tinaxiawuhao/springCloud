package com.example.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class IpFilter extends ZuulFilter {
    // IP黑名单列表
    private List<String> blackIpList = Arrays.asList("127.0.0.1");

    public IpFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        return false;
//        RequestContext ctx = RequestContext.getCurrentContext();
//        Object success = ctx.get("isSuccess");
//        return success == null ? true : Boolean.parseBoolean(success.toString());
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("msg", "你好吗");
        String ip = getRemoteHost(ctx.getRequest());
        //模拟 java.lang.ArithmeticException：/by zero。
//        System.out.println(2/0);
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {
            /*ctx.setSendZuulResponse(false) 告诉 Zuul 不需要将当前请求转发到后端的服务。原理体现在 shouldFilter() 方法上
            *
            * @Override
                public boolean shouldFilter() {
                    RequestContext ctx = RequestContext.getCurrentContext();
                    return (ctx.getRouteHost() == null && ctx.get(SERVICE_ID_KEY) != null && ctx.sendZuulResponse());
                }*/
            ctx.setSendZuulResponse(false);
            /*代码“ctx.set("sendForwardFilter.ran"，true);”是用来拦截本地转发请求的，当我们配置了 forward：/local 的路由，
            ctx.setSendZuulResponse(false) 对 forward 是不起作用的，需要设置 ctx.set("sendForwardFilter.ran"，true) 才行。

            protected static final String SEND_FORWARD_FILTER_RAN = "sendForwardFilter.ran";
                @Override
                public boolean shouldFilter() {
                    RequestContext ctx = RequestContext.getCurrentContext();
                    return ctx.containsKey(FORWARD_TO_KEY) && !ctx.getBoolean(SEND_FORWARD_FILTER_RAN, false);
                }
            */
            ctx.set("sendForwardFilter.ran", true);
            ctx.set("isSuccess",false);
            ctx.setResponseBody("在黑名单中");
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }

    /**
     * 获取用户IP地址
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }
}