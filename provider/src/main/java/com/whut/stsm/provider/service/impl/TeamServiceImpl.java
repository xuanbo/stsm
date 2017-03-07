package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.TeamDTO;
import com.whut.stsm.common.dto.UserTeamDTO;
import com.whut.stsm.common.service.TeamService;
import com.whut.stsm.common.util.Page;
import com.whut.stsm.provider.dao.TeamRepository;
import com.whut.stsm.provider.dao.UserTeamRepository;
import com.whut.stsm.provider.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by null on 2017/3/7.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.TeamService")
@Transactional(value = "jpaTxManager")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public TeamDTO findById(Long id) {
        return teamRepository.findOne(id);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public Page<TeamDTO> findByUserId(Long userId, Page<TeamDTO> page) {
        page.setCount(userTeamRepository.countByUserId(userId));
        Pageable pageable = new PageRequest(page.getCurrent() - 1, page.getSize());
        List<Long> teamIds = userTeamRepository.findByUserId(userId, pageable).getContent()
                .stream().map(UserTeamDTO::getTeamId).collect(Collectors.toList());
        page.setList(teamRepository.findAll(teamIds));
        return page;
    }
}
