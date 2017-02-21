package com.whut.stsm.common.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * dubbo服务的实体要实现序列化
 *
 * Created by null on 2017/2/20.
 */
@Entity
@Table(name = "t_demo")
public class DemoDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Length(min = 8, max = 18)
    @Column
    private String name;

    public DemoDTO() {
    }

    public DemoDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
