package com.whut.stsm.web.controller;

import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.web.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DemoController
 *
 * Created by null on 2017/2/20.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/listAll")
    public List<DemoDTO> listAll() {
        return demoService.listAll();
    }

    @GetMapping("/show")
    public String show() {
        return "{\"flag\": true, \"msg\": \"成功\"}";
    }

}
