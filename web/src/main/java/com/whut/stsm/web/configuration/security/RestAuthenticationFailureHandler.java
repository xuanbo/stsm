package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.web.util.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证失败处理
 * 认证失败后修改为JSON异常信息
 *
 * Created by null on 2017/2/22.
 */
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 自定义身份认证失败处理
     *
     * @param httpServletRequest    HttpServletRequest
     * @param httpServletResponse   HttpServletResponse
     * @param e AuthenticationException，身份认证异常
     * @throws IOException  I/O exception
     * @throws ServletException Servlet Exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        Response.writeJsonAndFlush(httpServletResponse, ResultDTO.fail(401, e.getMessage()));
    }
}
