package com.example.hystrixcore;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixCoreTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        HystrixRequestContext context = HystrixRequestContext.initializeContext();
//        String result = new MyHystrixCommand("zhangsan").execute();
//        System.out.println(result);
//        Future<String> future = new MyHystrixCommand("zhangsan").queue();
//        System.out.println(future.get());
//        context.shutdown();

//        String result = new MyHystrixCommand("zhangsan").execute();
//        System.out.println(result);
//        Future<String> future = new MyHystrixCommand("zhangsan").queue();
//        System.out.println(future.get());

//        HystrixRequestContext context = HystrixRequestContext.initializeContext();
//        String result = new ClearCacheHystrixCommand("zhangsan").execute();
//        System.out.println(result);
//        ClearCacheHystrixCommand.flushCache("zhangsan");
//        Future<String> future = new ClearCacheHystrixCommand("zhangsan").queue();
//        System.out.println(future.get());

        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("zhangsan").queue();
        Future<String> f2 = new MyHystrixCollapser("zhangsan333").queue();
        System.out.println(f1.get() + "=" + f2.get());
        context.shutdown();
    }
}
