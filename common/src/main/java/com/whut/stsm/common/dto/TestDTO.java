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
 * Created by null on 2017/3/8.
 */
@Entity
@Table(name = "t_test")
@Data
public class TestDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long userId;

    @Column
    private String name;

    @Column
    private String content;

    @Column
    private String description;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Column
    private String state;

}
