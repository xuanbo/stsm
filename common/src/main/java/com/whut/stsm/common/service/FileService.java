package com.whut.stsm.common.service;

import com.whut.stsm.common.dto.FileDTO;

/**
 * Created by null on 2017/3/10.
 */
public interface FileService {

    FileDTO save(FileDTO fileDTO);

    FileDTO findById(Long id);

    FileDTO findByTestId(Long testId);

    FileDTO findByTaskFormId(Long taskFormId);

}
