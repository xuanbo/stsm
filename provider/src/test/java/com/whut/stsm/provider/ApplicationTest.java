package com.whut.stsm.provider;

import org.flowable.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by null on 2017/2/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

    @Autowired
    private DataSource druidDataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void showDB() {
        log.debug("DataSource[{}]", druidDataSource.getClass());
    }

    @Test
    public void showTxManager() {
        log.debug("PlatformTransactionManager[{}]", transactionManager.getClass());
    }

    @Test
    public void showProcessDefinitions() {
        log.debug("Number of process definitions: {}", repositoryService.createDeploymentQuery().count());
    }
}
