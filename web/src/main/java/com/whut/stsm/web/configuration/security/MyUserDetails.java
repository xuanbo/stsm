package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 实现UserDetails
 *
 * Created by null on 2017/2/22.
 */
public class MyUserDetails implements UserDetails {

    private UserDTO user;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(UserDTO user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账号过期时间大于当前时间则没有过期
     *
     * @return 账号过期返回true
     */
    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountExpiredDate().getTime() > new Date().getTime();
    }

    /**
     * 失败尝试登录5次后锁定账号
     * 不使用数据库存储登录失败尝试次数，后面采用缓存实现
     *
     * @return 返回true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码过期时间大于当前时间则没有过期
     *
     * @return 密码过期返回true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsExpiredDate().getTime() > new Date().getTime();
    }

    /**
     * 用户是否可用
     *
     * @return 可用返回true
     */
    @Override
    public boolean isEnabled() {
        return user.getEnable();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MyUserDetails && getUsername().equals(((MyUserDetails) obj).getUsername());
    }

    @Override
    public int hashCode() {
        return getUsername().hashCode();
    }
}
