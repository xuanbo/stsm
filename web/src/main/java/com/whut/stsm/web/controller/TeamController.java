package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.TeamDTO;
import com.whut.stsm.common.service.TeamService;
import com.whut.stsm.common.util.Check;
import com.whut.stsm.common.util.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by null on 2017/3/7.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    @Reference
    private TeamService teamService;

    @ApiOperation(value = "根据id查询团队", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", required = true)
    })
    @GetMapping("/{id}")
    public ResultDTO<?> findById(@PathVariable Long id) {
        return ResultDTO.success(null, teamService.findById(id));
    }

    @ApiOperation(value = "查询用户的团队", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", required = true),
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/search")
    public ResultDTO<?> findByUserId(@RequestParam Long userId, Page<TeamDTO> page) {
        return ResultDTO.success(null, teamService.findByUserId(userId, page));
    }
}
