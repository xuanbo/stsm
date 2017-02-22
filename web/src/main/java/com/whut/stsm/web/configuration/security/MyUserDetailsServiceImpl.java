package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.dto.RoleDTO;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Spring Security获取用户信息，从而进行身份认证
 *
 * Created by null on 2017/2/22.
 */
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        RoleDTO roleDTO = userDTO.getRoleDTO();
        if (roleDTO == null) {
            throw new UsernameNotFoundException("账号还没有授予权限");
        }
        return new MyUserDetails(userDTO, getAuthorities(roleDTO));
    }

    /**
     * 获取权限
     * 我这里用户跟角色是一对一
     *
     * @param roleDTO 角色
     * @return Collection<GrantedAuthority>
     */
    private Collection<GrantedAuthority> getAuthorities(RoleDTO roleDTO) {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleDTO.getName());
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

}
