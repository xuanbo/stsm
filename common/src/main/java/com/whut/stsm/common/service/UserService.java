package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.UserDTO;

/**
 * Created by null on 2017/2/22.
 */
public interface UserService {

    UserDTO findByUsername(String username);

    /**
     * 登录失败，登录尝试次数加1
     *
     * @param username 用户名
     * @return 操作成功返回1
     */
    int loginFailure(String username);

    /**
     * 重置锁定
     *
     * @param username 用户名
     * @return 操作成功返回1
     */
    int resetLocked(String username);

    UserDTO save(UserDTO userDTO);

}
