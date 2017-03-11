package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.FileDTO;
import com.whut.stsm.common.dto.ProcessDefinitionDTO;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.dto.TestDTO;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.common.util.Check;
import com.whut.stsm.common.util.Page;
import com.whut.stsm.web.context.UserContext;
import com.whut.stsm.web.util.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * 流程Controller
 *
 * Created by null on 2017/2/28.
 */
@RestController
@RequestMapping("/flowable")
public class FlowableController {

    private static final Logger log = LoggerFactory.getLogger(FlowableController.class);

    private static final String fileRootPath = "D:\\file";

    @Reference
    private FlowableService flowableService;

    @Reference
    private UserService userService;

    @ApiOperation(value = "根据taskId查询具体任务", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", required = true)
    })
    @GetMapping("/task/{id}")
    public ResultDTO<?> findTask(@PathVariable String id) {
        return ResultDTO.success(null, flowableService.findTask(id));
    }

    @ApiOperation(value = "根据assignee查询个人任务", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "assignee", required = true),
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/tasks/{assignee}")
    public ResultDTO<?> findTasks(@PathVariable String assignee, Page<TaskDTO> page) {
        if (Check.isEmpty(assignee)) {
            return ResultDTO.fail(415, "assignee不能为空");
        }
        return ResultDTO.success("success", flowableService.findTask(assignee, page));
    }

    @ApiOperation(value = "查询流程定义", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page")
    })
    @GetMapping("/processDefinition")
    public ResultDTO<?> findProcessDefinition(Page<ProcessDefinitionDTO> page) {
        return ResultDTO.success("success", flowableService.findProcessDefinition(page));
    }

    @ApiOperation(value = "获取流程图", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "deploymentId", required = true),
            @ApiImplicitParam(paramType = "query", name = "resourceName", required = true)
    })
    @GetMapping("/processDefinition/{id}/diagram")
    public void getProcessDefinitionDiagram(@PathVariable String id, HttpServletResponse response) {
        response.setContentType("image/png");
        try {
            byte[] bytes = flowableService.getResourceAsBytes(id);
            FileCopyUtils.copy(bytes, response.getOutputStream());
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

    @ApiOperation(value = "开启测试流程", response = ResultDTO.class, produces = "multipart/form-data; charset=UTF-8")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "file"),
            @ApiImplicitParam(paramType = "form", name = "testDTO", required = true)
    })
    @PostMapping(value = "/process/start")
    public ResultDTO<?> startTestProcess(@RequestPart(required = false) MultipartFile file, TestDTO testDTO) {
        log.debug("testDTO[{}]", testDTO);
        // 流程清单填写者
        String username = UserContext.getCurrentUsername();
        UserDTO userDTO = userService.findByUsername(username);
        testDTO.setUserId(userDTO.getId());
        try {
            // 保存附件
            FileDTO fileDTO = FileUtil.copy(fileRootPath, getFileRelativePath(username), file);
            // 开启流程
            flowableService.startTestProcess(testDTO, fileDTO);
            return ResultDTO.success("开启成功");
        } catch (IOException e) {
            log.error("{}", e);
            return ResultDTO.fail(500, "文件上传出现错误");
        }
    }

    /**
     * 获取文件相对路径(年/月/日/username)
     *
     * @param username 用户账号
     * @return 文件相对路径
     */
    private String getFileRelativePath(String username) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        int month = localDate.getMonth().getValue();
        int day = localDate.getDayOfMonth();
        return year + File.separator + month + File.separator + day + File.separator + username;
    }

}
