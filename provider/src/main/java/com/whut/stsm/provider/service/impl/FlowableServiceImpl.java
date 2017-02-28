package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.task.Task;
import org.flowable.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
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

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String key, String username) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", username);
        runtimeService.startProcessInstanceByKey(key, variables);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<Task> findTask(String username, Page<Task> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(username)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        long count = taskQuery.count();
        page.setCount(count);
        List<Task> tasks = taskQuery.listPage(page.getOffset(), page.getSize());
        page.setList(tasks);
        return page;
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<Task> findTask(String username, Date after, Date before, Page<Task> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(username)
                .taskCreatedAfter(after)
                .taskCreatedBefore(before)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc();
        long count = taskQuery.count();
        page.setCount(count);
        List<Task> tasks = taskQuery.listPage(page.getOffset(), page.getSize());
        page.setList(tasks);
        return page;
    }

    @Override
    @Transactional(value = "transactionManager")
    public void completeTask(String taskId) {
        taskService.complete(taskId);
    }
}
