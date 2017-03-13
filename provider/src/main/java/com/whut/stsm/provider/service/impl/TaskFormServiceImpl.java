package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.TaskFormDTO;
import com.whut.stsm.common.service.TaskFormService;
import com.whut.stsm.provider.dao.TaskFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by null on 2017/3/13.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.TaskFormService")
@Transactional("jpaTxManager")
public class TaskFormServiceImpl implements TaskFormService {

    @Autowired
    private TaskFormRepository taskFormRepository;

    @Override
    @Transactional("jpaTxManager")
    public TaskFormDTO save(TaskFormDTO taskFormDTO) {
        return taskFormRepository.save(taskFormDTO);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public TaskFormDTO findById(Long id) {
        return taskFormRepository.findOne(id);
    }
}
