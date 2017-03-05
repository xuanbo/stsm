package com.whut.stsm.web.configuration.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码错误异常
 *
 * Created by null on 2017/3/5.
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException() {
        super("验证码错误");
    }

    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

}
