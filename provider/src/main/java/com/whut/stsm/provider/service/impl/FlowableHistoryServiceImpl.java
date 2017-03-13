package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.HistoryTaskDTO;
import com.whut.stsm.common.service.FlowableHistoryService;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricTaskInstance;
import org.flowable.engine.history.HistoricTaskInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Page<HistoryTaskDTO> findTask(String username, Page<HistoryTaskDTO> page) {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        return pageHelper(historicTaskInstanceQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<HistoryTaskDTO> findTask(String username, Date after, Date before, Page<HistoryTaskDTO> page) {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(username)
                .taskCreatedAfter(after)
                .taskCreatedBefore(before)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        return pageHelper(historicTaskInstanceQuery, page);
    }

    private Page<HistoryTaskDTO> pageHelper(HistoricTaskInstanceQuery historicTaskInstanceQuery, Page<HistoryTaskDTO> page) {
        long count = historicTaskInstanceQuery.count();
        page.setCount(count);
        List<HistoricTaskInstance> historicTaskInstances = historicTaskInstanceQuery.listPage(page.getOffset(), page.getSize());
        List<HistoryTaskDTO> historyTaskDTOS = new ArrayList<>(historicTaskInstances.size());
        historicTaskInstances.forEach(historicTaskInstance -> historyTaskDTOS.add(new HistoryTaskDTO(historicTaskInstance)));
        page.setList(historyTaskDTOS);
        return page;
    }

}
