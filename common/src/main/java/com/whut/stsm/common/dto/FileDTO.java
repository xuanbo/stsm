package com.whut.stsm.common.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 任务表单关联的附件
 *
 * Created by null on 2017/3/8.
 */
@Entity
@Table(name = "t_file")
@Data
public class FileDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long taskFormId; // 每一个任务节点上传附件

    @Column
    private Long testId; // 提交测试流程时上传附件

    @Column
    private String name;

    @Column
    private String fileName;

    @Column
    private String url;

    @Column
    private String state;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Column
    private boolean show; // 文件是否删掉

}
