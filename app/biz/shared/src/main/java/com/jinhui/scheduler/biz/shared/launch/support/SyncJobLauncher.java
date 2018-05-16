package com.jinhui.scheduler.biz.shared.launch.support;

import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.core.task.TaskExecutor;

public class SyncJobLauncher extends SimpleJobLauncher {
    /**
     * 仅能使用内建的同步Executor，不允许设置Executor
     *
     * @param taskExecutor
     */
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        // do nothing
    }
}
