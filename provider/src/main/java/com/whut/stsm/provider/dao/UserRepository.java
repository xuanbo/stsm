package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by null on 2017/2/22.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByUsername(String username);

    @Query(value = "SELECT u.* FROM t_user u WHERE u.username REGEXP ?1" +
            " AND u.id IN(SELECT ut.user_id FROM t_user_team ut WHERE ut.team_id IN ?2) LIMIT 10", nativeQuery = true)
    List<UserDTO> findByUsernameLikeAndTeamIdIn(String usernameLike, List<Long> teamIds);

}
