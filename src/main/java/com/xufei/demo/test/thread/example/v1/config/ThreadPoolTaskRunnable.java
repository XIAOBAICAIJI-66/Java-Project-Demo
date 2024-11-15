package com.xufei.demo.test.thread.example.v1.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 将 Task<T> 包装成 Runnable，便于提交给线程池执行
 *
 * @param <T> 任务的参数类型
 */
@Data
@AllArgsConstructor
public class ThreadPoolTaskRunnable<T> implements Runnable {

    private ThreadPoolTask<T> task;

    private T t;

    @Override
    public void run() {
        try {
            task.handle(t);  // 执行任务逻辑
        } catch (Exception e) {
            // 异常处理
            System.out.println("任务执行失败：" + e.getMessage());
        } finally {
            task.completeNotify(t);  // 通知任务完成
        }
    }
}
