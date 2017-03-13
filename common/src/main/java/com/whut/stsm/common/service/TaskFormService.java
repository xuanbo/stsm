package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.TaskFormDTO;

/**
 * Created by null on 2017/3/13.
 */
public interface TaskFormService {

    TaskFormDTO save(TaskFormDTO taskFormDTO);

    TaskFormDTO findById(Long id);

}
