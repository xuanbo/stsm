package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.DemoDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/2/21.
 */
@Repository
public interface DemoRepository extends CrudRepository<DemoDTO, Long> {
}
