package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by null on 2017/2/28.
 */
@RestController
@RequestMapping("/flowable")
public class FlowableController {

    @Reference
    private FlowableService flowableService;

    @GetMapping("/task/{username}")
    public Page<Task> findTask(@PathVariable String username, Page<Task> page) {
        return flowableService.findTask(username, page);
    }
}
