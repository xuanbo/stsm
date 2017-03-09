package com.whut.stsm.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.flowable.engine.repository.ProcessDefinition;

import java.io.Serializable;

/**
 * 流程定义
 *
 * Created by null on 2017/3/9.
 */
@Data
@NoArgsConstructor
public class ProcessDefinitionDTO implements Serializable {

    private String id;
    private String name;
    private String key;
    private Integer version;
    private String category;
    private String deploymentId;
    private String description;
    private String resourceName;
    private String diagramResourceName;

    public ProcessDefinitionDTO(ProcessDefinition processDefinition) {
        this.id = processDefinition.getId();
        this.name = processDefinition.getName();
        this.key = processDefinition.getKey();
        this.version = processDefinition.getVersion();
        this.category = processDefinition.getCategory();
        this.deploymentId = processDefinition.getDeploymentId();
        this.description = processDefinition.getDescription();
        this.resourceName = processDefinition.getResourceName();
        this.diagramResourceName = processDefinition.getDiagramResourceName();
    }

}
