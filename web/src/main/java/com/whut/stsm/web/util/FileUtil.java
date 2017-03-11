package com.whut.stsm.web.util;

import com.whut.stsm.common.dto.FileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件工具类
 *
 * Created by null on 2017/3/10.
 */
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 文件保存在服务端
     *
     * @param fileRootPath 文件要保存的跟目录
     * @param fileRelativePath 文件要保存的相对路径 (年/月/日/username)
     * @param file MultipartFile
     * @return FileDTO
     */
    public static FileDTO copy(String fileRootPath, String fileRelativePath, MultipartFile file)
            throws IOException {
        if (file.isEmpty()) {
            log.debug("file isEmpty");
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        log.debug("originalFilename[{}]", originalFilename);
        // 时间撮 + 原始文件名
        String serverFileName = System.currentTimeMillis() + originalFilename;
        log.debug("serverFileName[{}]", serverFileName);
        String filePath = fileRootPath + File.separator + fileRelativePath;
        log.debug("filePath[{}]", serverFileName);
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(new File(dir, serverFileName));

        FileDTO fileDTO = new FileDTO();
        fileDTO.setUrl(fileRelativePath);
        fileDTO.setOriginFileName(originalFilename);
        fileDTO.setServerFileName(serverFileName);
        fileDTO.setCreateDate(new Date());
        return fileDTO;
    }

}
