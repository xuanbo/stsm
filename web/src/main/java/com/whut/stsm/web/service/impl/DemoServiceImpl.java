package com.whut.stsm.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.web.service.DemoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 注意，这个是Spring的@Service
 *
 * 是服务消费端的业务逻辑，可能需要调用多个dubbo服务
 *
 * Created by null on 2017/2/20.
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    /**
     * 注入dubbo的服务
     */
    @Reference
    private com.whut.stsm.common.dubbo.service.DemoService demoService;

    @Override
    public List<DemoDTO> listAll() {
        return demoService.listAll();
    }
}
