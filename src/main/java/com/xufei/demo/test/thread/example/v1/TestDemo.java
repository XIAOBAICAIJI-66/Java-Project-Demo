package com.xufei.demo.test.thread.example.v1;

import com.xufei.demo.test.thread.example.v1.config.ThreadPoolConfigUtils;
import com.xufei.demo.test.thread.example.v1.config.ThreadPoolTask;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 启动类，模拟抢票过程
 */
public class TestDemo {

    public static void main(String[] args) throws InterruptedException {
        // 获取线程池配置
        ThreadPoolConfigUtils threadPoolConfig = new ThreadPoolConfigUtils();
        ThreadPoolExecutor executorService = threadPoolConfig.executorService();

        // 设置任务数量（例如20000）
        int ticketTaskCount = 10000;  // 电影票任务数量
        int carTaskCount = 10000;     // 汽车任务数量

        // 使用两个线程并行提交任务
        Thread ticketThread = new Thread(() -> submitTasks(threadPoolConfig, ConcertTicketTask.class, ticketTaskCount));
        Thread carThread = new Thread(() -> submitTasks(threadPoolConfig, CarPurchaseTask.class, carTaskCount));

        // 启动两个线程并行执行任务提交
        ticketThread.start();
        carThread.start();

        // 等待两个线程执行完成
        ticketThread.join();
        carThread.join();

        // 程序结束后可以调用 shutdown 来关闭线程池
        threadPoolConfig.shutdown();
    }

    /**
     * 动态提交任务
     *
     * @param threadPoolConfig 提交任务的线程池配置
     * @param taskClass        任务类
     * @param taskCount        任务数量
     * @param <T>              任务类型
     */
    private static <T> void submitTasks(ThreadPoolConfigUtils threadPoolConfig, Class<T> taskClass, int taskCount) {
        for (int i = 0; i < taskCount; i++) {
            try {
                // 创建任务实例
                T task = taskClass.getDeclaredConstructor().newInstance();

                // 提交任务到线程池
                if (task instanceof ThreadPoolTask) {
                    threadPoolConfig.submitTask((ThreadPoolTask<?>) task, null);
                }
            } catch (Exception e) {
                System.out.println("任务提交失败: " + e.getMessage());
            }
        }
    }
}