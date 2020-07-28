package com.example.zuuldemo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class PostFilter extends ZuulFilter {

    public PostFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public Object run() {
        HttpServletRequest req = (HttpServletRequest) RequestContext.getCurrentContext().getRequest();
        System.err.println("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
        StringBuilder params = new StringBuilder("?");
        // 获取URL参数
        Enumeration<String> names = req.getParameterNames();
        if (req.getMethod().equals("GET")) {
            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                params.append(name);
                params.append("=");
                params.append(req.getParameter(name));
                params.append("&");
            }
        }
        if (params.length() > 0) {
            params.delete(params.length() - 1, params.length());
        }
        System.err.println(
                "REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + params + " " + req.getProtocol());
        Enumeration<String> headers = req.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = req.getHeader(name);
            System.err.println("REQUEST:: > " + name + ":" + value);
        }
        final RequestContext ctx = RequestContext.getCurrentContext();
        // 获取请求体参数
        if (!ctx.isChunkedRequestBody()) {
            ServletInputStream inp = null;
            try {
                inp = ctx.getRequest().getInputStream();
                String body = null;
                if (inp != null) {
                    body = IOUtils.toString(inp);
                    System.err.println("REQUEST:: > " + body);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
