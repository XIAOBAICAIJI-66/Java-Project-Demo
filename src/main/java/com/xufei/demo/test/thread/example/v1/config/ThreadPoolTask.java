package com.xufei.demo.test.thread.example.v1.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 任务抽象类，定义任务的基本结构
 *
 * @param <T> 任务的参数类型
 */
@Data
@AllArgsConstructor
public abstract class ThreadPoolTask<T> {
    /**
     * 通知任务执行完成
     *
     * @param t 任务参数
     */
    public abstract void completeNotify(T t);

    /**
     * 执行任务的逻辑
     *
     * @param t 任务的参数
     */
    public abstract void  handle(T t) throws InterruptedException;
}
