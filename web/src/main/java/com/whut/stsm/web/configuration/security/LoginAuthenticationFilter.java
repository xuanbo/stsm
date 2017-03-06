package com.whut.stsm.web.configuration.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger log = LoggerFactory.getLogger(LoginAuthenticationFilter.class);

    @Value("${http.login.captchaParameter}")
    private String captchaParameter;

    @Value("${http.login.sessionCaptchaParameter}")
    private String sessionCaptchaParameter;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 处理验证码
        String captcha = request.getParameter(captchaParameter);
        String sessionCaptcha = (String) request.getSession().getAttribute(sessionCaptchaParameter);
        log.debug("输入的验证码：{}", captcha);
        if(captcha == null || !captcha.equalsIgnoreCase(sessionCaptcha)) {
            throw new CaptchaException("验证码不正确");
        }
        // 调用UsernamePasswordAuthenticationFilter去认证用户名和密码
        return super.attemptAuthentication(request, response);
    }
}
