package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.DemoDTO;

/**
 * 定义业务接口
 *
 * Created by null on 2017/2/20.
 */
public interface DemoService {

    DemoDTO findById(Long id);

    void save(DemoDTO demoDTO);

}
