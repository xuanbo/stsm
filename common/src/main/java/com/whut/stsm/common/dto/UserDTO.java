package com.whut.stsm.common.dto;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by null on 2017/2/22.
 */
@Entity
@Table(name = "t_user")
@Data
public class UserDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    // 账号过期时间，过期则不能进行认证
    @Column
    private Date accountExpiredDate;

    // 密码过期时间，过期则不能进行认证
    @Column
    private Date credentialsExpiredDate;

    // 用户是否可用，false则不能进行认证
    @Column
    private Boolean enable;

    // 其他用户相关属性...

    // 角色
    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleDTO roleDTO;

}
