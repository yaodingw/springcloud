package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class PaymentService {

    public String paymentInfoOk(Integer id) {
        return "threadPool: " + Thread.currentThread().getName() + "  paymentInfoOk  \t id:" +id ;
    }

    //@HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    //})
    @HystrixCommand
    public String paymentInfoTimeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "threadPool: " + Thread.currentThread().getName() + "  paymentInfoTimeout \t id:"+id;
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "调用支付接口超时或者异常 \t" + "\t 当前线程池名字" + Thread.currentThread().getName();
    }

    public String paymentGlobalFallbackMethod() {
        return "global hystrix 异常数据处理";
    }
}
