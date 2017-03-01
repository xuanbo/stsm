package com.whut.stsm.provider;

import com.whut.stsm.common.service.FlowableHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by null on 2017/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class FlowableHistoryServiceTest {

    private static final Logger log = LoggerFactory.getLogger(FlowableHistoryServiceTest.class);

    @Autowired
    private FlowableHistoryService flowableHistoryService;

    @Test
    public void findTaskVariables() {
    }

}
