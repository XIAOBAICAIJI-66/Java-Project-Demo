package com.xufei.demo.test.thread.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//public class ExecutorsDemo {
//    public static void main(String[] args) {
//        //自定义线程池
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(
//                10,
//                20,
//                60,
//                TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//        );
//        ThreadDemo1 thread = new ThreadDemo1();
//        for (int i = 0; i < 100; i++) {
//            executor.execute(thread);
//        }
//
//        executor.shutdown();  // 关闭线程池
//
//    }
//
//}
//
//@Data
//class ThreadDemo1 implements Runnable {
//
//    private int total = 10000;
//
//    private boolean flag = true;
//
//
//    @Override
//    public void run() {
//        while (flag) {
//            synchronized (ThreadDemo1.class) {
//                if (total > 0) {
//                    System.out.println(Thread.currentThread().getName() + "--->卖出第" + total + "张票");
//                    total--;
//                    if (total <= 0) {
//                        flag = false;
//                    }
//                }
//            }
//        }
//    }
//}

import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorsDemo {
    public static void main(String[] args) {
        // 自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        AtomicInteger total = new AtomicInteger(1000000);  // 使用 AtomicInteger

        // 提交任务
        for (int i = 0; i < 100; i++) {
            executor.execute(new ThreadDemo1(total));
        }

        executor.shutdown();  // 关闭线程池
    }
}

class ThreadDemo1 implements Runnable {

    private AtomicInteger total;  // 使用 AtomicInteger 代替普通的 int

    public ThreadDemo1(AtomicInteger total) {
        this.total = total;
    }

    @Override
    public void run() {
        // 不再需要 synchronized 锁
        while (true) {
            int currentTicket = total.get();
            // 没票了，退出循环
            if (currentTicket <= 0) break;
            if (total.compareAndSet(currentTicket, currentTicket - 1)) {
                System.out.println(Thread.currentThread().getName() + "---> 卖出第" + currentTicket + "张票");
            }
        }
    }
}
