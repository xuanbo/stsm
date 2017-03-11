package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.UserDTO;
import com.whut.stsm.common.util.Page;

import java.util.List;

/**
 * Created by null on 2017/2/22.
 */
public interface UserService {

    UserDTO findByUsername(String username);

    /**
     * 根据usernameLike模糊查询username团队中的用户，只取前10条结果
     *
     * @param username 调用方法者
     * @param usernameLike 查询的用户
     * @return List<UserDTO>
     */
    List<UserDTO> findByUsernameLike(String username, String usernameLike);

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

    Page<UserDTO> findByTeamId(Long teamId, Page<UserDTO> page);
}
