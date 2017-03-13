package com.whut.stsm.provider.dao;

import com.whut.stsm.common.dto.FileDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by null on 2017/3/8.
 */
@Repository
public interface FileRepository extends JpaRepository<FileDTO, Long> {

    FileDTO findByTestId(Long testId);

    FileDTO findByTaskFormId(Long taskFormId);

}
