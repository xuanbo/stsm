package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.task.Task;

import java.util.Date;
import java.util.Map;

/**
 * 流程服务接口
 *
 * Created by null on 2017/2/28.
 */
public interface FlowableService {

    /***************************************************************************************
     *  process
     **************************************************************************************/
    void startProcessInstanceByKey(String key, Map<String, Object> variables);

    /***************************************************************************************
     *  task
     **************************************************************************************/
    Page<TaskDTO> findTask(String assignee, Page<TaskDTO> page);

    Page<TaskDTO> findTask(String assignee, Date after, Date before, Page<TaskDTO> page);

    void completeTask(String taskId, Map<String, Object> variables);
}
