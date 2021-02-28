package com.atguigu.springcloud.service;

import com.atguigu.springcloud.service.impl.PaymentHystrixFallbackServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentHystrixFallbackServiceImpl.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String getPaymentInfoOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String getPaymentInfoTimeout(@PathVariable("id") Integer id);

}
