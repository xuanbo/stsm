package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.service.DemoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * DemoController
 *
 * Created by null on 2017/2/20.
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 注入dubbo服务
     */
    @Reference
    private DemoService demoService;

    @ApiOperation(value = "根据id获取demo", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "demoId")
    })
    @GetMapping("/{id}")
    public ResultDTO<?> show(@PathVariable Long id) {
        return ResultDTO.success(null, demoService.findById(id));
    }

    @ApiOperation(value = "保存", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "demoDTO", required = true)
    })
    @PostMapping("/save")
    public ResultDTO<?> save(@Valid @RequestBody DemoDTO demoDTO) {
        demoService.save(demoDTO);
        return ResultDTO.success("success");
    }

}
