package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.common.service.DemoService;
import com.whut.stsm.provider.dao.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注意：这里是dubbo的`@Service`
 *
 * `@Component`被Spring管理，声明一个Bean
 * `@Service`就是dubbo本身的注解,接口暴露
 *
 * Created by null on 2017/2/20.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.DemoService")
@Transactional("jpaTxManager")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override
    public DemoDTO findById(Long id) {
        return demoRepository.findOne(id);
    }

}
