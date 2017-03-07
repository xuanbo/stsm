package com.whut.stsm.web.util;

import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HttpServletResponse工具类
 *
 * Created by null on 2017/3/6.
 */
public class Response {

    public static void writeJsonAndFlush(HttpServletResponse response, ResultDTO<?> resultDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        // 设置返回json
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        // 认证成功
        writer.append(JsonUtil.parse(resultDTO));
        writer.flush();
    }

}
