package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.service.FlowableHistoryService;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricTaskInstance;
import org.flowable.engine.history.HistoricTaskInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by null on 2017/2/28.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.FlowableHistoryService")
@Transactional("transactionManager")
public class FlowableHistoryServiceImpl implements FlowableHistoryService {

    @Autowired
    private HistoryService historyService;

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<HistoricTaskInstance> findTask(String username, Page<HistoricTaskInstance> page) {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        long count = historicTaskInstanceQuery.count();
        page.setCount(count);
        List<HistoricTaskInstance> tasks = historicTaskInstanceQuery.listPage(page.getOffset(), page.getSize());
        page.setList(tasks);
        return page;
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<HistoricTaskInstance> findTask(String username, Date after, Date before, Page<HistoricTaskInstance> page) {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .taskCreatedAfter(after)
                .taskCreatedBefore(before)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        long count = historicTaskInstanceQuery.count();
        page.setCount(count);
        List<HistoricTaskInstance> tasks = historicTaskInstanceQuery.listPage(page.getOffset(), page.getSize());
        page.setList(tasks);
        return page;
    }

}
