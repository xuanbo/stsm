package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.util.Check;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.task.Task;
import org.flowable.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2017/2/28.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.FlowableService")
@Transactional("transactionManager")
public class FlowableServiceImpl implements FlowableService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String key, Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(key);
        } else {
            runtimeService.startProcessInstanceByKey(key, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String key, Map<String, Object> variables, String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(key);
        } else {
            runtimeService.startProcessInstanceByKey(key, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String key, String businessKey, Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(key, businessKey);
        } else {
            runtimeService.startProcessInstanceByKey(key, businessKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String key, String businessKey, Map<String, Object> variables, String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(key, businessKey);
        } else {
            runtimeService.startProcessInstanceByKey(key, businessKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public TaskDTO findTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return Check.isNull(task) ? new TaskDTO() : new TaskDTO(task);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<TaskDTO> findTask(String assignee, Page<TaskDTO> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        return pageHelper(taskQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<TaskDTO> findTask(String assignee, Date after, Date before, Page<TaskDTO> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .taskCreatedAfter(after)
                .taskCreatedBefore(before)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc();
        return pageHelper(taskQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void setTaskVariables(String taskId, Map<String, Object> variables) {
        taskService.setVariables(taskId, variables);
    }

    @Override
    @Transactional(value = "transactionManager")
    public Map<String, Object> getTaskVariables(String taskId) {
        return taskService.getVariables(taskId);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void completeTask(String taskId, Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            taskService.complete(taskId);
        } else {
            taskService.complete(taskId, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public String getBusinessKeyByTaskId(String taskId) {
        TaskDTO task = findTask(taskId);
        return runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult()
                .getBusinessKey();
    }

    private Page<TaskDTO> pageHelper(TaskQuery taskQuery, Page<TaskDTO> page) {
        long count = taskQuery.count();
        page.setCount(count);
        List<Task> tasks = taskQuery.listPage(page.getOffset(), page.getSize());
        final List<TaskDTO> taskDTOS = new ArrayList<>(tasks.size());
        tasks.forEach(task -> taskDTOS.add(new TaskDTO(task)));
        page.setList(taskDTOS);
        return page;
    }
}
