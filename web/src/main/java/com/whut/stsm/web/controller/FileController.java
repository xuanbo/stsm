package com.whut.stsm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.FileDTO;
import com.whut.stsm.common.dto.ResultDTO;
import com.whut.stsm.common.service.FileService;
import com.whut.stsm.web.util.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by null on 2017/3/13.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Reference
    private FileService fileService;


    @ApiOperation(value = "文件id下载文件", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", required = true)
    })
    @GetMapping("/{id}/download")
    public ResponseEntity<?> download(@PathVariable Long id) {
        FileDTO fileDTO = fileService.findById(id);
        try {
            byte[] outStreamBytes = FileUtil.copy(fileDTO);
            String fileName = new String(fileDTO.getOriginFileName().getBytes("UTF-8"), "iso8859-1");
            return FileUtil.download(fileName, outStreamBytes);
        } catch (IOException e) {
            log.error("{}", e);
            return new ResponseEntity<>("附件下载失败", HttpStatus.OK);
        }
    }

    @ApiOperation(value = "根据testId查询", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "testId", required = true)
    })
    @GetMapping("/findByTestId")
    public ResultDTO<?> findByTestId(@RequestParam Long testId) {
        return ResultDTO.success(null, fileService.findByTestId(testId));
    }

    @ApiOperation(value = "根据taskFormId查询", response = ResultDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "taskFormId", required = true)
    })
    @GetMapping("/findByTaskFormId")
    public ResultDTO<?> findByTaskFormId(@RequestParam Long taskFormId) {
        return ResultDTO.success(null, fileService.findByTaskFormId(taskFormId));
    }

}
