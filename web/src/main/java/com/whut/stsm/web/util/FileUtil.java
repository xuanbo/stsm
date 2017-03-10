package com.whut.stsm.web.util;

import com.whut.stsm.common.dto.FileDTO;
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
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        // 时间撮 + 原始文件名
        String serverFileName = System.currentTimeMillis() + originalFilename;
        String filePath = fileRootPath + File.separator + fileRelativePath;
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
