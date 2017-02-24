package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 登录失败限制AuthenticationProvider
 *
 * Created by null on 2017/2/22.
 */
public class LimitLoginAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService cacheUserService;

    /**
     * 重写身份认证
     *
     * @param authentication 用户登录时的信息
     * @return Authentication  身份认证信息
     * @throws AuthenticationException 身份认证异常
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        // 用户名
        String username = token.getName();
        //  导入UserDetails，判断账号是否有效，为null说明查询不到用户，账号不存在
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("账号不存在");
        }

        /*------------------------------------------------------------
         *       判断账号是否能正确使用 开始
         *-----------------------------------------------------------*/
        if (!userDetails.isEnabled()) {
            throw new DisabledException("账号已被禁用");
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException("密码错误5次，账号已被锁定");
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期");
        }
        /*------------------------------------------------------------
         *       判断账号是否能正确使用 结束
         *-----------------------------------------------------------*/

        /*------------------------------------------------------------
         *       验证密码是否正确 开始
         *-----------------------------------------------------------*/
        //  数据库用户的密码(已经加密)
        String password = userDetails.getPassword();
        //  与authentication里面的credentials相比较，加密在这里体现
        if (!passwordEncoder.matches(token.getCredentials().toString(), password)) {
            //  登录尝试失败后尝试次数加一
            cacheUserService.loginFailure(username);
            throw new BadCredentialsException("密码错误");
        }
        /*------------------------------------------------------------
         *       验证密码是否正确 结束
         *-----------------------------------------------------------*/

        //  账号通过认证则授权
        cacheUserService.resetLocked(username);
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //  返回true后才会执行上面的authenticate方法，这步能确保authentication能正确转换类型
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

    /**
     * 设置UserDetailsService
     *
     * @param userDetailsService 设置自定义的UserDetailsService实现类
     */
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 设置PasswordEncoder
     *
     * @param passwordEncoder 设置自定义的PasswordEncoder实现类
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
