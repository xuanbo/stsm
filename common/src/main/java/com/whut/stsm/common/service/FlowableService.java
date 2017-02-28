package com.whut.stsm.common.service;

import com.whut.stsm.common.util.Page;
import org.flowable.engine.task.Task;

import java.util.Date;

/**
 * 流程服务接口
 *
 * Created by null on 2017/2/28.
 */
public interface FlowableService {

    /***************************************************************************************
     *  task
     **************************************************************************************/
    Page<Task> findTask(String username, Page<Task> page);

    Page<Task> findTask(String username, Date after, Date before, Page<Task> page);

    void completeTask(String taskId);
}
