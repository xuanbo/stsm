package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.TeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/3/7.
 */
@Repository
public interface TeamRepository extends JpaRepository<TeamDTO, Long> {
}
