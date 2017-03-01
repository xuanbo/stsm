package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.util.Page;

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

    void startProcessInstanceByKey(String key, Map<String, Object> variables, String owner);

    void startProcessInstanceByKey(String key, String businessKey, Map<String, Object> variables);

    void startProcessInstanceByKey(String key, String businessKey, Map<String, Object> variables, String owner);

    /***************************************************************************************
     *  task
     **************************************************************************************/
    TaskDTO findTask(String taskId);

    Page<TaskDTO> findTask(String assignee, Page<TaskDTO> page);

    Page<TaskDTO> findTask(String assignee, Date after, Date before, Page<TaskDTO> page);

    void setTaskVariables(String taskId, Map<String, Object> variables);

    Map<String, Object> getTaskVariables(String taskId);

    void completeTask(String taskId, Map<String, Object> variables);

    String getBusinessKeyByTaskId(String taskId);
}
