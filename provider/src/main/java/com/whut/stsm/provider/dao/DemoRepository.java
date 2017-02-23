package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.DemoDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/2/21.
 */
@Repository
public interface DemoRepository extends CrudRepository<DemoDTO, Long> {

    /**
     * 采用spring jpa的命名规范定义方法
     *
     * @param name 对象的属性名
     * @return DemoDTO
     */
    DemoDTO findByName(String name);

}
