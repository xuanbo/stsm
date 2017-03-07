package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.TaskFormDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/3/7.
 */
@Repository
public interface TaskFormRepository extends JpaRepository<TaskFormDTO, Long> {
}
