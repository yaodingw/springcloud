package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String getPaymentInfoOk(@PathVariable("id") Integer id) {
        return paymentHystrixService.getPaymentInfoOk(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    //})
    @HystrixCommand
    public String getPaymentInfoTimeout(@PathVariable("id") Integer id) {
        return paymentHystrixService.getPaymentInfoTimeout(id);
    }

    public String paymentInfoTimeoutHandler(Integer id) {
        return "order调用支付接口超时或者异常 \t" + "\t 当前线程池名字" + Thread.currentThread().getName();
    }


}
