package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.dto.UserTeamDTO;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.common.util.Page;
import com.whut.stsm.provider.cache.UserCache;
import com.whut.stsm.provider.dao.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by null on 2017/2/22.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.UserService")
@Transactional(value = "jpaTxManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCache userCache;

    @Autowired
    private UserTeamRepository userTeamRepository;

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public UserDTO findByUsername(String username) {
        return userCache.findByUsername(username).orElse(null);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public List<UserDTO> findByUsernameLike(String username, String usernameLike) {
        Optional<UserDTO> userDTOOptional = userCache.findByUsername(username);
        if (!userDTOOptional.isPresent()) {
            return null;
        }
        // 查询该用户所在的团队
        UserDTO userDTO = userDTOOptional.get();
        UserTeamDTO userTeamDTO = new UserTeamDTO();
        userTeamDTO.setUserId(userDTO.getId());
        List<Long> teamIds = userTeamRepository.findAll(Example.of(userTeamDTO))
                .stream().map(UserTeamDTO::getTeamId).collect(Collectors.toList());
        return userCache.findByUsernameLike(usernameLike, teamIds);
    }

    @Override
    @Transactional(value = "jpaTxManager")
    public UserDTO save(UserDTO userDTO) {
        return userCache.save(userDTO);
    }

    @Override
    @Transactional(value = "jpaTxManager")
    public void loginFailure(String username) {
        userCache.loginFailure(username);
    }

    @Override
    @Transactional(value = "jpaTxManager")
    public int getLoginAttemptTimes(String username) {
        return userCache.getLoginAttemptTimes(username);
    }

    @Override
    @Transactional(value = "jpaTxManager")
    public void resetLoginAttemptTimes(String username) {
        userCache.resetLoginAttemptTimes(username);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public Page<UserDTO> findByTeamId(Long teamId, Page<UserDTO> page) {
        page.setCount(userTeamRepository.countByTeamId(teamId));
        Pageable pageable = new PageRequest(page.getCurrent() - 1, page.getSize());
        List<Long> userIds = userTeamRepository.findByTeamId(teamId, pageable).getContent()
                .stream().map(UserTeamDTO::getUserId).collect(Collectors.toList());
        page.setList(userCache.findAll(userIds));
        return page;
    }
}
