package com.whut.stsm.common.dto;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(RoleDTO roleDTO) {
        this.roleDTO = roleDTO;
    }

    public Date getAccountExpiredDate() {
        return accountExpiredDate;
    }

    public void setAccountExpiredDate(Date accountExpiredDate) {
        this.accountExpiredDate = accountExpiredDate;
    }

    public Date getCredentialsExpiredDate() {
        return credentialsExpiredDate;
    }

    public void setCredentialsExpiredDate(Date credentialsExpiredDate) {
        this.credentialsExpiredDate = credentialsExpiredDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountExpiredDate=" + accountExpiredDate +
                ", credentialsExpiredDate=" + credentialsExpiredDate +
                ", enable=" + enable +
                ", roleDTO=" + roleDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (!id.equals(userDTO.id)) return false;
        if (!username.equals(userDTO.username)) return false;
        if (!password.equals(userDTO.password)) return false;
        if (!accountExpiredDate.equals(userDTO.accountExpiredDate)) return false;
        if (!credentialsExpiredDate.equals(userDTO.credentialsExpiredDate)) return false;
        if (!enable.equals(userDTO.enable)) return false;
        return roleDTO.equals(userDTO.roleDTO);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + accountExpiredDate.hashCode();
        result = 31 * result + credentialsExpiredDate.hashCode();
        result = 31 * result + enable.hashCode();
        result = 31 * result + roleDTO.hashCode();
        return result;
    }
}
