package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/2/22.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByUsername(String username);

}
