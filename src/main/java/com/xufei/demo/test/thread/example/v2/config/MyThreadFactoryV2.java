package com.xufei.demo.test.thread.example.v2.config;

import org.springframework.lang.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂的常见用途
 * 命名线程：为每个线程指定唯一的名称，便于日志记录和调试。
 * 设置优先级：你可以为线程设置不同的优先级，例如可以让某些线程拥有较高的优先级。
 * 设置守护线程：线程池中的线程默认是非守护线程。如果需要，可以设置线程为守护线程（thread.setDaemon(true)），这样当应用退出时，守护线程会自动结束。
 * 线程组：可以将线程添加到特定的 ThreadGroup 中，这样有助于组织和管理线程。
 */
public class MyThreadFactoryV2 implements ThreadFactory {
    @Override
    public Thread newThread(@NonNull Runnable r) {
        return new Thread(r, "test_threadPool_" + r.hashCode());
    }
}