package com.whut.stsm.common.service;

import com.whut.stsm.common.util.Page;
import org.flowable.engine.history.HistoricTaskInstance;

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
    Page<HistoricTaskInstance> findTask(String username, Page<HistoricTaskInstance> page);

    Page<HistoricTaskInstance> findTask(String username, Date after, Date before, Page<HistoricTaskInstance> page);

}
