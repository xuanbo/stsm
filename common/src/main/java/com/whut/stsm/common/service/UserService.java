package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.UserDTO;

/**
 * Created by null on 2017/2/22.
 */
public interface UserService {

    UserDTO findByUsername(String username);

    UserDTO save(UserDTO userDTO);

    /**
     * 登录失败，失败尝试次数加1
     *
     * @param username 用户名
     */
    void loginFailure(String username);

    /**
     * 获取用户尝试登录次数，超过5次则锁定
     *
     * @param username 用户名
     * @return 尝试登录次数
     */
    int getLoginAttemptTimes(String username);

    void resetLoginAttemptTimes(String username);
}
