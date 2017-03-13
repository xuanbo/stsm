package com.whut.stsm.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.whut.stsm.common.dto.FileDTO;
import com.whut.stsm.common.service.FileService;
import com.whut.stsm.provider.dao.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by null on 2017/3/10.
 */
@Component
@Service(interfaceName = "com.whut.stsm.common.service.FileService")
@Transactional("jpaTxManager")
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    @Transactional("jpaTxManager")
    public FileDTO save(FileDTO fileDTO) {
        return fileRepository.save(fileDTO);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public FileDTO findById(Long id) {
        return fileRepository.findOne(id);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public FileDTO findByTestId(Long testId) {
        return fileRepository.findByTestId(testId);
    }

    @Override
    @Transactional(value = "jpaTxManager", readOnly = true)
    public FileDTO findByTaskFormId(Long taskFormId) {
        return fileRepository.findByTaskFormId(taskFormId);
    }
}
