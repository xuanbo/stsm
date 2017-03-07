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
 * 团队
 *
 * Created by null on 2017/3/7.
 */
@Entity
@Table(name = "t_team")
@Data
public class TeamDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

}
