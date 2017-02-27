package com.whut.stsm.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoDTO implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Length(min = 8, max = 18)
    @Column
    private String name;

}