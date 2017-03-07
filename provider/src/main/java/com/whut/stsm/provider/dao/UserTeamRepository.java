package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.UserTeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/3/7.
 */
@Repository
public interface UserTeamRepository extends JpaRepository<UserTeamDTO, Long> {

    Page<UserTeamDTO> findByUserId(Long userId, Pageable pageable);

    long countByUserId(Long userId);

    long countByTeamId(Long teamId);

    Page<UserTeamDTO> findByTeamId(Long teamId, Pageable pageable);

}
