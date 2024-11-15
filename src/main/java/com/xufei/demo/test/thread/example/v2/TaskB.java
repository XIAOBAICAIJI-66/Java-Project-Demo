package com.xufei.demo.test.thread.example.v2;

import com.xufei.demo.test.thread.example.v2.config.ThreadPoolTaskRunnableV2;


public class TaskB extends ThreadPoolTaskRunnableV2<TaskTypeB> {
    public TaskB(TaskTypeB taskTypeB) {
        super(taskTypeB);
    }

    @Override
    protected void handle(TaskTypeB taskTypeB) {

    }

    @Override
    protected void completeNotify(TaskTypeB taskTypeB) {

    }
}
