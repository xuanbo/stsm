package com.whut.stsm.provider;

import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by null on 2017/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class FlowableServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FlowableServiceTest.class);

    @Autowired
    private FlowableService flowableService;

    /**
     * 启动流程实例，通过流程变量设置任务办理人
     */
    @Test
    public void startProcessInstance() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", "user");
        flowableService.startProcessInstanceByKey("demo", variables);
//        flowableService.startProcessInstanceByKey("demo", "demoId", variables);
//        flowableService.startProcessInstanceByKey("demo", "demoId", variables, "bobo");
    }

    @Test
    public void findTask() {
        log.debug("{}", flowableService.findTask("lisi", new Page<>(1, 10)));
    }

    /**
     * 完成任务，通过流程变量设置下一个任务办理人
     */
    @Test
    public void completeTask() {
        // 完成任务之前，设置流程变量
        Map<String, Object> variables = new HashMap<>();
//        variables.put("reason", "回家养猪");
//        flowableService.setTaskVariables("10", variables);

        variables = new HashMap<>();
        variables.put("username", "lisi");
        flowableService.completeTask("5014", variables);
    }

    @Test
    public void getTaskVariables() {
        flowableService.getTaskVariables("2503").forEach((key, value) -> log.debug("{} -> {}", key, value));
    }

    @Test
    public void getBusinessKey() {
        log.debug("{}", flowableService.getBusinessKeyByTaskId("7502"));

    }

}
