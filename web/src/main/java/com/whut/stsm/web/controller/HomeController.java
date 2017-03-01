package com.whut.stsm.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by null on 2017/2/16.
 */
@Controller
public class HomeController {

    /**
     * cloud-config-repo中的属性
     */
    @Value("${hello}")
    private String hello;

    /**
     * 注入web-ui模块打包后的静态资源首页
     */
    @Value("classpath:/ui/index.html")
    private Resource indexHtml;

    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("msg", hello);
        modelMap.addAttribute("sessionId", session.getId());
        return "index";
    }

    /**
     * web-ui页面
     *
     * @return ResponseEntity<Resource>
     */
    @GetMapping("/ui")
    @ResponseBody
    public ResponseEntity<Resource> ui_index() {
        return ResponseEntity.ok().body(indexHtml);
    }

}
