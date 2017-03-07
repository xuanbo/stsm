package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.TeamDTO;
import com.whut.stsm.common.service.TeamService;
import com.whut.stsm.common.util.Page;
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

    @GetMapping("/{id}")
    public ResultDTO<?> findById(@PathVariable Long id) {
        return ResultDTO.success(null, teamService.findById(id));
    }

    @GetMapping("/search")
    public ResultDTO<?> findByUserId(@RequestParam Long userId, Page<TeamDTO> page) {
        return ResultDTO.success(null, teamService.findByUserId(userId, page));
    }
}
