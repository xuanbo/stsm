package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.util.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录认证成功处理
 * 返回认证成功的JSON信息
 *
 * Created by null on 2017/2/22.
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 自定义身份认证成功处理
     *
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @param authentication Authentication
     * @throws IOException  I/O exception
     * @throws ServletException Servlet Exception
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        // 设置返回json
        httpServletResponse.setContentType("application/json");
        PrintWriter writer = httpServletResponse.getWriter();
        // 认证成功
        writer.append(JsonUtil.parse(ResultDTO.success("认证成功！")));
        writer.flush();
    }

}
