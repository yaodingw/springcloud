package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String getPaymentInfoOk(Integer id) {
        return "--- global getPaymentInfoOk --";
    }

    @Override
    public String getPaymentInfoTimeout(Integer id) {
        return "--- global getPaymentInfoTimeout ---";
    }
}
