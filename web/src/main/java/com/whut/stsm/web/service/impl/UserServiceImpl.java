package com.whut.stsm.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by null on 2017/2/22.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Reference
    private com.whut.stsm.common.dubbo.service.UserService userService;

    @Override
    public UserDTO findByUsername(String username) {
        return userService.findByUsername(username);
    }
}
