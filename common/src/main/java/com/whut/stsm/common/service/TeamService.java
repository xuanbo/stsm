package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.TeamDTO;
import com.whut.stsm.common.util.Page;

/**
 * Created by null on 2017/3/7.
 */
public interface TeamService {

    TeamDTO findById(Long id);

    Page<TeamDTO> findByUserId(Long userId, Page<TeamDTO> page);

}
