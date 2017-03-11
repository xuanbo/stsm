package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.common.util.Page;
import com.whut.stsm.web.context.UserContext;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by null on 2017/3/7.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @ApiOperation(value = "获取当前认证用户信息", response = ResultDTO.class)
    @GetMapping("/me")
    public ResultDTO<?> me() {
        String username = UserContext.getCurrentUsername();
        UserDTO userDTO = userService.findByUsername(username);
        userDTO.setPassword(null);
        return ResultDTO.success(null, userDTO);
    }

    @ApiOperation(value = "查询团队成员", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "teamId", required = true),
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/searchByTeamId")
    public ResultDTO<?> findByTeamId(@RequestParam Long teamId, Page<UserDTO> page) {
        return ResultDTO.success(null, userService.findByTeamId(teamId, page));
    }

    @ApiOperation(value = "根据username模糊查询", response = ResultDTO.class, notes = "只查询前10条记录，跟查询的用户在一个团队")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "usernameLike", required = true)
    })
    @GetMapping("/searchByUsernameLike")
    public ResultDTO<?> findByUsernameLike(@RequestParam String usernameLike) {
        return ResultDTO.success(null, userService.findByUsernameLike(UserContext.getCurrentUsername(), usernameLike));
    }
}
