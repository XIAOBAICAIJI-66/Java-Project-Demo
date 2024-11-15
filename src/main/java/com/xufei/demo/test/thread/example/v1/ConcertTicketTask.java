package com.xufei.demo.test.thread.example.v1;

import com.xufei.demo.test.thread.example.v1.config.ThreadPoolTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 抢音乐会门票任务类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
class ConcertTicketTask extends ThreadPoolTask<Integer> {

    private static final AtomicInteger movieTicketCount = new AtomicInteger(10000);  // 初始化电影票数量为10000

    @Override
    public void completeNotify(Integer integer) {
        int i = movieTicketCount.get() + 1;
        System.out.println(Thread.currentThread().getName() + " 抢到" + i + "号票");
    }

    @Override
    public void handle(Integer integer) throws InterruptedException {
        int ticketNumber = movieTicketCount.get();
        if (ticketNumber > 0) {
            movieTicketCount.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " 票号：" + ticketNumber);
            //休眠一秒
            Thread.sleep(100);
        } else {
            System.out.println(Thread.currentThread().getName() + " 电影票已抢光！");
        }

    }
}