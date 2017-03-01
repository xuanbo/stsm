package com.whut.stsm.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.history.HistoricTaskInstance;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 历史任务
 *
 * Created by null on 2017/3/1.
 */
@Data
@NoArgsConstructor
public class HistoryTaskDTO implements Serializable {

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
    // 任务创建时间
    private Date createTime;
    // 持续时间
    private Date dueDate;
    private Date endTime;
    // 流程变量
    private Map<String, Object> processVariables;

    public HistoryTaskDTO(HistoricTaskInstance historicTaskInstance) {
        this.id = historicTaskInstance.getId();
        this.name = historicTaskInstance.getName();
        this.assignee = historicTaskInstance.getAssignee();
        this.description = historicTaskInstance.getDescription();
        this.owner = historicTaskInstance.getOwner();
        this.parentTaskId = historicTaskInstance.getParentTaskId();
        this.executionId = historicTaskInstance.getExecutionId();
        this.priority = historicTaskInstance.getPriority();
        this.processDefinitionId = historicTaskInstance.getProcessDefinitionId();
        this.processInstanceId = historicTaskInstance.getProcessDefinitionId();
        this.taskDefinitionKey = historicTaskInstance.getTaskDefinitionKey();
        this.tenantId = historicTaskInstance.getTenantId();
        this.createTime = historicTaskInstance.getCreateTime();
        this.dueDate = historicTaskInstance.getDueDate();
        this.endTime = historicTaskInstance.getEndTime();
        this.processVariables = historicTaskInstance.getProcessVariables();
    }

}
