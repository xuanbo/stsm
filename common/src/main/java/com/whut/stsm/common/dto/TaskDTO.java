package com.whut.stsm.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.task.DelegationState;
import org.flowable.engine.task.Task;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务
 *
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


    // 关联业务表
    private TestDTO testDTO;
    // 上一个任务节点关联的表单
    private TaskFormDTO beforeTaskFormDTO;
    // 工作流任务节点关联的表单
    private TaskFormDTO currentTaskFormDTO;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.assignee = task.getAssignee();
        this.category = task.getCategory();
        this.description = task.getDescription();
        this.executionId = task.getExecutionId();
        this.owner = task.getOwner();
        this.parentTaskId = task.getParentTaskId();
        this.priority = task.getPriority();
        this.processDefinitionId = task.getProcessDefinitionId();
        this.processInstanceId = task.getProcessInstanceId();
        this.taskDefinitionKey = task.getTaskDefinitionKey();
        this.tenantId = task.getTenantId();
        this.delegationState = task.getDelegationState();
        this.createTime = task.getCreateTime();
        this.dueDate = task.getDueDate();
    }

}
