package com.whut.stsm.common.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * 工作流任务节点关联的表单
 *
 * Created by null on 2017/3/7.
 */
@Entity
@Table(name = "t_task_form")
@Data
public class TaskFormDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code; // 200：正常，500：不正常

    @Column
    private String description;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Column
    private String state; // 任务状态

    @Column
    private Long taskId; // 任务id

    @Column
    private Long userId; // 任务创建者id


    @Transient
    private String assignee;

}
