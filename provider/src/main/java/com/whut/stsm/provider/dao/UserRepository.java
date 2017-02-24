package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by null on 2017/2/22.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByUsername(String username);

    @Modifying
    @Query("update UserDTO u set u.attemptTimes = u.attemptTimes + 1, u.lastAttemptDate = ?2 where u.username = ?1")
    int loginFailure(String username, Date lastAttemptDate);

    @Modifying
    @Query("update UserDTO u set u.attemptTimes = 0 where u.username = ?1")
    int resetLocked(String username);
}
