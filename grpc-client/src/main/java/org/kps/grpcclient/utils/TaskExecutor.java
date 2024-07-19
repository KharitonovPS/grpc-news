package org.kps.grpcclient.utils;

import org.springframework.core.task.VirtualThreadTaskExecutor;

public class TaskExecutor {

    public static final VirtualThreadTaskExecutor VIRTUAL_THREAD_TASK_EXECUTOR = new VirtualThreadTaskExecutor(
            FutureConverter.class.getName() + "-pool");
}
