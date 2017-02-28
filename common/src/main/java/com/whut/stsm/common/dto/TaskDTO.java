package com.whut.stsm.common.dto;

import lombok.Builder;
import lombok.Data;
import org.flowable.engine.task.DelegationState;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by null on 2017/2/28.
 */
@Data
@Builder
public class TaskDTO implements Serializable {

    private String id;
    private String name;
    private String description;
    private int priority;
    private Date createTime;
    private Date dueDate;
    private int suspensionState;
    private String category;
    private String executionId;
    private String processInstanceId;
    private String processDefinitionId;
    private String taskDefinitionKey;
    private String formKey;
    private boolean isCountEnabled;
    private int variableCount;
    private int identityLinkCount;
    private String tenantId;
    private Date claimTime;
    private String owner;
    private String originalAssignee;
    private String assignee;
    private String parentTaskId;
    private DelegationState delegationState;

}
