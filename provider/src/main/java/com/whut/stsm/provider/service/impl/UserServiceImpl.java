package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.UserService;
import com.whut.stsm.provider.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by null on 2017/2/22.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.UserService")
@Transactional("jpaTxManager")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public int loginFailure(String username) {
        return userRepository.loginFailure(username, new Date());
    }

    @Override
    public int resetLocked(String username) {
        return userRepository.resetLocked(username);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userRepository.save(userDTO);
    }
}
