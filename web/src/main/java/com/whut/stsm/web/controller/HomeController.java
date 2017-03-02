package com.whut.stsm.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("msg", hello);
        modelMap.addAttribute("sessionId", session.getId());
        return "index";
    }

}
