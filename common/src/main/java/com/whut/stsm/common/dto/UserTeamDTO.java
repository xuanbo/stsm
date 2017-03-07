package com.whut.stsm.common.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 用户-团队关联
 *
 * Created by null on 2017/3/7.
 */
@Entity
@Table(name = "t_user_team")
@Data
public class UserTeamDTO implements Serializable {

    @Id
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long teamId;

}
