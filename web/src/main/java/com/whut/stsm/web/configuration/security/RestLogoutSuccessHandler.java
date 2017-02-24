package com.whut.stsm.web.configuration.security;

import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.util.JsonUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注销成功返回json
 *
 * Created by null on 2017/2/22.
 */
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        // 设置返回json
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        // 认证成功
        writer.append(JsonUtil.parse(ResultDTO.success("注销成功！")));
        writer.flush();
    }
}
