package com.xufei.demo.test.thread.example.v1.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfigUtils {

    //核心线程数
    private static final int CORE_POOL_SIZE = 10;

    //最大线程数
    private static final int MAX_POOL_SIZE = 20;

    //线程存活时间
    private static final long KEEP_ALIVE_TIME = 60L;

    //队列大小
    private static final int QUEUE_CAPACITY = 100;

    //拒绝策略
    private static final RejectedExecutionHandler executionHandler = new ThreadPoolExecutor.DiscardPolicy();

    private ThreadPoolExecutor threadPoolExecutor;

    @Bean
    public ThreadPoolExecutor executorService() {

        //创建队列
//        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        //创建无界队列
        //创建自定义线程工厂
        MyThreadFactory myThreadFactory = new MyThreadFactory();

        //初始化线程池对象,并赋值给全局变量
        this.threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,//核心线程数
                MAX_POOL_SIZE,//最大线程数
                KEEP_ALIVE_TIME,//线程存活时间
                TimeUnit.SECONDS,//存活时间单位
                queue,//队列信息
                myThreadFactory,//线程工厂信息
                executionHandler//拒绝策略
        );
        System.out.println("启动了 = " + this.threadPoolExecutor);
        return threadPoolExecutor;
    }

    /**
     * 提交任务到线程池
     */
    public <T> void submitTask(ThreadPoolTask<T> task, T t) {
        ThreadPoolTaskRunnable<T> runnable = new ThreadPoolTaskRunnable<>(task, t);
        try {
            // 提交任务到线程池执行
            threadPoolExecutor.execute(runnable);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    /**
     * 关闭项目时确保线程池关闭
     */
    @PreDestroy
    public void shutdown() {
        System.out.println("结束了 = " + this.threadPoolExecutor);
        if (this.threadPoolExecutor != null) {
            this.threadPoolExecutor.shutdown();  // 优雅地关闭线程池
        }
    }
}



