package com.xufei.demo.test.thread.example.v2.config;

import com.xufei.demo.test.thread.example.v1.config.ThreadPoolTask;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 将 Task<T> 包装成 Runnable，便于提交给线程池执行
 *
 * @param <T> 任务的参数类型
 */
@Data
@AllArgsConstructor
public class ThreadPoolTaskRunnableV2<T> implements Runnable {

    private T t;  // 任务的参数，作为字段保存，便于在 run 方法中使用

    @Override
    public void run() {
        try {
            // 执行任务的处理逻辑
            handle(t);
            // 任务执行完成后的通知
            completeNotify(t);
        } catch (Exception e) {
            // 异常处理
            System.out.println("任务执行失败：" + e.getMessage());
        }
    }

    // 任务的处理逻辑
    protected void handle(T t) {
        // 这里可以定义任务的实际执行逻辑
        System.out.println("执行任务逻辑：" + t);
    }

    // 任务完成后的通知
    protected void completeNotify(T t) {
        // 这里可以定义任务完成后的通知逻辑
        System.out.println("任务完成通知：" + t);
    }
}
