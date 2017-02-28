package com.whut.stsm.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.task.DelegationState;
import org.flowable.engine.task.Task;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by null on 2017/2/28.
 */
@Data
@NoArgsConstructor
public class TaskDTO implements Serializable {

    private String id;
    private String name;
    // 任务办理人
    private String assignee;
    // 任务类型
    private String category;
    // 任务描述
    private String description;
    // 任务执行id
    private String executionId;
    // 任务所有者
    private String owner;
    // 任务父ID
    private String parentTaskId;
    // 任务优先级
    private int priority;
    // 流程定义的id
    private String processDefinitionId;
    // 流程实例的id
    private String processInstanceId;
    // 任务定义的key
    private String taskDefinitionKey;
    // 所有人ID
    private String tenantId;
    // 任务委派状态
    private DelegationState delegationState;
    // 任务创建时间
    private Date createTime;
    // 持续时间
    private Date dueDate;
    // 流程变量
    private Map<String, Object> processVariables;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getId();
        this.assignee = task.getId();
        this.category = task.getId();
        this.description = task.getId();
        this.executionId = task.getId();
        this.owner = task.getId();
        this.parentTaskId = task.getId();
        this.priority = task.getId();
        this.processDefinitionId = task.getId();
        this.processInstanceId = task.getId();
        this.taskDefinitionKey = task.getId();
        this.tenantId = task.getId();
        this.delegationState = task.getId();
        this.createTime = task.getId();
        this.dueDate = task.getId();
        this.processVariables = task.getId();
    }

}
