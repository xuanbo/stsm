package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.HistoryTaskDTO;
import com.whut.stsm.common.util.Page;

import java.util.Date;

/**
 * 流程历史查询
 *
 * Created by null on 2017/2/28.
 */
public interface FlowableHistoryService {

    /***************************************************************************************
     *  history task
     **************************************************************************************/
    Page<HistoryTaskDTO> findTask(String assignee, Page<HistoryTaskDTO> page);

    Page<HistoryTaskDTO> findTask(String assignee, Date after, Date before, Page<HistoryTaskDTO> page);

}
