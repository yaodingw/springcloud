package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int i = paymentService.create(payment);
        if (i > 0) {
            return new CommonResult(200, "插入数据库成功", i);
        }
        return new CommonResult(500, "插入数据库失败", i);
    }

    @GetMapping("/payment/get/{id}")
    @Transactional
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        log.info("当前服务的提供端口号是: {}", serverPort);
        Payment paymentById = paymentService.getPaymentById(id);
        if (null != paymentById) {
            return new CommonResult(200, "查询成功,服务端口："+serverPort, paymentById);
        }
        return new CommonResult(500, "查询失败", id);
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentServerPort(){
        return serverPort;
    }


    @GetMapping(value = "/payment/feign")
    public String paymentFeignTimeout() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}

