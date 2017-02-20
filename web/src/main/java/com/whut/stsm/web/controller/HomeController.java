package com.whut.stsm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by null on 2017/2/16.
 */
@Controller
public class HomeController {

    @GetMapping({"/", "/index"})
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("msg", "welcome!");
        modelMap.addAttribute("sessionId", session.getId());
        return "index";
    }

}
