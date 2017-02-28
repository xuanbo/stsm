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

/**
 * Created by null on 2017/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class FlowableServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FlowableServiceTest.class);

    @Autowired
    private FlowableService flowableService;

    @Test
    public void startProcessInstance() {
        flowableService.startProcessInstanceByKey("demo", "zhangsan");
    }

    @Test
    public void findTask() {
        log.debug("{}", flowableService.findTask("zhangsan", new Page<>(1, 10)));
    }

}
