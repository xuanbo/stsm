package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.util.Check;
import com.whut.stsm.common.util.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程Controller
 *
 * Created by null on 2017/2/28.
 */
@RestController
@RequestMapping("/flowable")
public class FlowableController {

    @Reference
    private FlowableService flowableService;

    @ApiOperation(value = "根据assignee查询个人任务", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "assignee", required = true),
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/task/{assignee}")
    public ResultDTO<?> findTask(@PathVariable String assignee, Page<TaskDTO> page) {
        if (Check.isEmpty(assignee)) {
            return ResultDTO.fail(415, "assignee不能为空");
        }
        return ResultDTO.success("success", flowableService.findTask(assignee, page));
    }
}
