package com.atguigu.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class MyLB implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);


    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current > 100 ? 0 : current + 1;

        } while (!atomicInteger.compareAndSet(current, next));
        log.info("当前调用次数：{}", next);
        System.out.println("当前调用次数："+next);
        return next;

    }


    @Override
    public ServiceInstance getInstance(List<ServiceInstance> serviceInstanceList) {
        int andIncrement = getAndIncrement();
        return serviceInstanceList.get(andIncrement % serviceInstanceList.size());
    }
}
