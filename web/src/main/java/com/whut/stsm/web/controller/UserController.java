package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.common.util.Page;
import com.whut.stsm.web.configuration.security.MyUserDetails;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String username = getCurrentUsername();
        UserDTO userDTO = userService.findByUsername(username);
        userDTO.setPassword(null);
        return ResultDTO.success(null, userDTO);
    }

    @ApiOperation(value = "查询团队成员", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "teamId", required = true),
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/search")
    public ResultDTO<?> findByTeamId(@RequestParam Long teamId, Page<UserDTO> page) {
        return ResultDTO.success(null, userService.findByTeamId(teamId, page));
    }

    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            return ((MyUserDetails) principal).getUsername();
        }
        return null;
    }
}
