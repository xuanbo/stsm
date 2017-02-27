package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.provider.cache.UserCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by null on 2017/2/22.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.UserService")
@Transactional(value = "jpaTxManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCache userCache;

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public UserDTO findByUsername(String username) {
        return userCache.findByUsername(username).orElse(null);
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
}
