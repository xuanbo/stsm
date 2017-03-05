package com.whut.stsm.web.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录认证filter，在这里处理验证码，然后调用UsernamePasswordAuthenticationFilter去认证用户名和密码
 *
 * Created by null on 2017/3/5.
 */
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 处理验证码

        // 调用UsernamePasswordAuthenticationFilter去认证用户名和密码
        return super.attemptAuthentication(request, response);
    }
}
