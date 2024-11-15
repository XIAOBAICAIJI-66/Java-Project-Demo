package com.xufei.demo.test.thread.example.v1;

import com.xufei.demo.test.thread.example.v1.config.ThreadPoolTask;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
class CarPurchaseTask extends ThreadPoolTask<Integer> {
    private static final AtomicInteger carStock = new AtomicInteger(10000);  // 汽车库存为10000

    @Override
    public void completeNotify(Integer integer) {
        int i = carStock.get() + 1;
        System.out.println(Thread.currentThread().getName() + " 抢到" + i + "号汽车");
    }

    @Override
    public void handle(Integer integer) throws InterruptedException {
        int i = carStock.get();
        if (i > 0) {
            carStock.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " 车号：" + i);
            //休眠一秒
            Thread.sleep(100);
        } else {
            System.out.println(Thread.currentThread().getName() + " 汽车已抢光！");
        }

    }
}
