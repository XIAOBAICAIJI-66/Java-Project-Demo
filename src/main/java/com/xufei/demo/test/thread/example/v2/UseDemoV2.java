package com.xufei.demo.test.thread.example.v2;

import com.xufei.demo.test.thread.example.v2.config.ThreadPoolConfigUtilsV2;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UseDemoV2 {
    @Resource
    private ThreadPoolConfigUtilsV2 threadPoolConfigUtils;

    // 提交任务类型 A
    public void submitTask() {
        TaskTypeB taskTypeB = new TaskTypeB("Task A", 123);
        TaskB taskB = new TaskB(taskTypeB);

        threadPoolConfigUtils.submitTask(taskB);
    }
}
