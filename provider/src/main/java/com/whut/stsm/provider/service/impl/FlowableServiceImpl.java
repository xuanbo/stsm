package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.FileDTO;
import com.whut.stsm.common.dto.ProcessDefinitionDTO;
import com.whut.stsm.common.dto.TaskDTO;
import com.whut.stsm.common.dto.TestDTO;
import com.whut.stsm.common.service.FileService;
import com.whut.stsm.common.service.FlowableService;
import com.whut.stsm.common.service.TestService;
import com.whut.stsm.common.util.Check;
import com.whut.stsm.common.util.Page;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.task.Task;
import org.flowable.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2017/2/28.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.FlowableService")
@Transactional("transactionManager")
public class FlowableServiceImpl implements FlowableService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IdentityService identityService;


    @Autowired
    private TestService testService;

    @Autowired
    private FileService fileService;

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey);
        } else {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables, String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey);
        } else {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String processDefinitionKey, String businessKey,
                                          Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        } else {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceByKey(String processDefinitionKey, String businessKey,
                                          Map<String, Object> variables, String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        } else {
            runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceById(String processDefinitionId, Map<String, Object> variables, String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceById(processDefinitionId);
        } else {
            runtimeService.startProcessInstanceById(processDefinitionId, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public void startProcessInstanceById(String processDefinitionId, String businessKey, Map<String, Object> variables,
                                         String owner) {
        identityService.setAuthenticatedUserId(owner);
        if (Check.isEmpty(variables)) {
            runtimeService.startProcessInstanceById(processDefinitionId, businessKey);
        } else {
            runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<ProcessDefinitionDTO> findProcessDefinition(Page<ProcessDefinitionDTO> page) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().desc();
        return pageHelper(processDefinitionQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public byte[] getResourceAsBytes(String processDefinitionId) throws IOException {
        InputStream inputStream = repositoryService.getProcessDiagram(processDefinitionId);
        return FileCopyUtils.copyToByteArray(inputStream);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public TaskDTO findTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return Check.isNull(task) ? new TaskDTO() : new TaskDTO(task);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<TaskDTO> findTask(String assignee, Page<TaskDTO> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc()
                .orderByTaskDueDate().asc();
        return pageHelper(taskQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager", readOnly = true)
    public Page<TaskDTO> findTask(String assignee, Date after, Date before, Page<TaskDTO> page) {
        TaskQuery taskQuery = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .taskCreatedAfter(after)
                .taskCreatedBefore(before)
                .orderByTaskPriority().desc()
                .orderByTaskCreateTime().desc();
        return pageHelper(taskQuery, page);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void setTaskVariables(String taskId, Map<String, Object> variables) {
        taskService.setVariables(taskId, variables);
    }

    @Override
    @Transactional(value = "transactionManager")
    public Map<String, Object> getTaskVariables(String taskId) {
        return taskService.getVariables(taskId);
    }

    @Override
    @Transactional(value = "transactionManager")
    public void completeTask(String taskId, Map<String, Object> variables) {
        if (Check.isEmpty(variables)) {
            taskService.complete(taskId);
        } else {
            taskService.complete(taskId, variables);
        }
    }

    @Override
    @Transactional(value = "transactionManager")
    public String getBusinessKeyByTaskId(String taskId) {
        TaskDTO task = findTask(taskId);
        return runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult()
                .getBusinessKey();
    }

    /**
     * 这里采用了jpa和mybatis操作数据库，采用jap的事务管理器
     *
     * @param testDTO TestDTO
     * @param fileDTO fileDTO
     */
    @Override
    @Transactional(value = "jpaTxManager")
    public void startTestProcess(TestDTO testDTO, FileDTO fileDTO) {
        // 保存测试清单
        TestDTO persistTestDTO = testService.save(testDTO);
        // 保存测试清单附件
        if (Check.isNotNull(fileDTO)) {
            fileDTO.setTestId(persistTestDTO.getId());
            fileService.save(fileDTO);
        }
        // 启动测试流程
        Map<String, Object> variables = new HashMap<>();
        variables.put("username", testDTO.getAssignee());
        String owner = persistTestDTO.getUserId().toString();
        this.startProcessInstanceById(persistTestDTO.getProcessDefinitionId(), variables, owner);
    }

    /*********************************************************************************
     * 分页处理
     ********************************************************************************/
    private Page<TaskDTO> pageHelper(TaskQuery taskQuery, Page<TaskDTO> page) {
        long count = taskQuery.count();
        page.setCount(count);
        List<Task> tasks = taskQuery.listPage(page.getOffset(), page.getSize());
        final List<TaskDTO> taskDTOS = new ArrayList<>(tasks.size());
        tasks.forEach(task -> taskDTOS.add(new TaskDTO(task)));
        page.setList(taskDTOS);
        return page;
    }

    private Page<ProcessDefinitionDTO> pageHelper(ProcessDefinitionQuery processDefinitionQuery,
                                                  Page<ProcessDefinitionDTO> page) {
        long count = processDefinitionQuery.count();
        page.setCount(count);
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.listPage(page.getOffset(), page.getSize());
        final List<ProcessDefinitionDTO> processDefinitionDTOS = new ArrayList<>(processDefinitions.size());
        processDefinitions.forEach(processDefinition ->
                processDefinitionDTOS.add(new ProcessDefinitionDTO(processDefinition)));
        page.setList(processDefinitionDTOS);
        return page;
    }
}
