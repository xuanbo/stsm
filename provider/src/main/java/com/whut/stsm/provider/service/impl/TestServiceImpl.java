package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.TestDTO;
import com.whut.stsm.common.service.TestService;
import com.whut.stsm.provider.dao.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by null on 2017/3/9.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.TestService")
@Transactional(value = "jpaTxManager")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Override
    @Transactional(value = "jpaTxManager")
    public TestDTO save(TestDTO testDTO) {
        return testRepository.save(testDTO);
    }
}
