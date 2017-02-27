package com.whut.stsm.common.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by null on 2017/2/22.
 */
@Entity
@Table(name = "t_role")
@Data
public class RoleDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

}
