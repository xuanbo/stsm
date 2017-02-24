package com.whut.stsm.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 缓存user
 *
 * Created by null on 2017/2/23.
 */
@Service("cacheUserService")
@CacheConfig(cacheNames = "com.whut.stsm.userCache")
public class CacheUserServiceImpl implements UserService {

    @Reference
    private UserService userService;

    @Override
    @Cacheable(key = "'com.whut.stsm.cache.user:' + #username")
    public UserDTO findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @Override
    @CacheEvict(key = "'com.whut.stsm.cache.user:' + #username")
    public int loginFailure(String username) {
        return userService.loginFailure(username);
    }

    @Override
    @CacheEvict(key = "'com.whut.stsm.cache.user:' + #username")
    public int resetLocked(String username) {
        return userService.resetLocked(username);
    }

    @Override
    @CachePut(key = "'com.whut.stsm.cache.user:' + #userDTO.username")
    public UserDTO save(UserDTO userDTO) {
        return userService.save(userDTO);
    }
}
