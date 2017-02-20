package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.common.dubbo.service.DemoService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意：这里是dubbo的`@Service`
 *
 * `@Component`被Spring管理，声明一个Bean
 * `@Service`就是dubbo本身的注解,接口暴露
 *
 * Created by null on 2017/2/20.
 */
@Component
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public List<DemoDTO> listAll() {
        List<DemoDTO> list = new ArrayList<>();
        list.add(new DemoDTO(1L, "demo1"));
        list.add(new DemoDTO(2L, "demo2"));
        return list;
    }

}
