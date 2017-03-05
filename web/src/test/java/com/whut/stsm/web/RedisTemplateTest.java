package com.whut.stsm.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by null on 2017/2/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class RedisTemplateTest {

    private static final Logger log = LoggerFactory.getLogger(RedisTemplateTest.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ApplicationContext ac;

    @Autowired
    private RedisOperationsSessionRepository sessionRepository;

    @Test
    public void show() {
        log.debug("redisTemplate[{}]", redisTemplate.getClass());
        String[] beanNamesForType = ac.getBeanNamesForType(RedisTemplate.class);
        for (int i = 0; i < beanNamesForType.length; i++) {
            log.debug("{} -> {}", i + 1, beanNamesForType[i]);
        }

        log.debug("RedisOperationsSessionRepository[{}]", sessionRepository);
    }
}
