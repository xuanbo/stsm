package com.whut.stsm.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.DemoDTO;
import com.whut.stsm.common.service.DemoService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 缓存Demo
 *
 * Created by null on 2017/2/23.
 */
@Service("cacheDemoService")
@CacheConfig(cacheNames = "com.whut.stsm.demoCache")
public class CacheDemoServiceImpl implements DemoService {

    /**
     * 注入dubbo服务
     */
    @Reference
    private DemoService demoService;

    @Override
    @Cacheable(key = "'com.whut.stsm.cache.demo:' + #id")
    public DemoDTO findById(Long id) {
        return demoService.findById(id);
    }
}
