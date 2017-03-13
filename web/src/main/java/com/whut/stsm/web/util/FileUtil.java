package com.whut.stsm.web.util;

import com.whut.stsm.common.dto.FileDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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

    // 文件根目录
    private static final String FILE_ROOT_PATH = "D:\\file";


    /**
     * 文件保存在服务端
     *
     * @param fileRelativePath 文件要保存的相对路径 (年/月/日/username)
     * @param file MultipartFile
     * @return FileDTO
     */
    public static FileDTO copy(String fileRelativePath, MultipartFile file)
            throws IOException {
        if (isEmpty(file)) {
            log.debug("file isEmpty");
            return null;
        }
        String originalFilename = file.getOriginalFilename();
        log.debug("originalFilename[{}]", originalFilename);
        // 时间撮 + 原始文件名
        String serverFileName = System.currentTimeMillis() + originalFilename;
        log.debug("serverFileName[{}]", serverFileName);
        String filePath = FILE_ROOT_PATH + File.separator + fileRelativePath;
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

    /**
     * 将文件复制到byte[]
     *
     * @param fileDTO FileDTO
     * @return byte[]
     * @throws IOException I/O exception
     */
    public static byte[] copy(FileDTO fileDTO) throws IOException {
        // 文件在服务端的路径
        String path = FILE_ROOT_PATH + File.separator + fileDTO.getUrl() + File.separator + fileDTO.getServerFileName();
        return FileCopyUtils.copyToByteArray(new File(path));
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名
     * @param src 文件字节数组
     * @return ResponseEntity<byte[]>
     */
    public static ResponseEntity<?> download(String fileName, byte[] src) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(src, headers, HttpStatus.CREATED);
    }

    private static boolean isEmpty(MultipartFile file) {
        return file == null || file.isEmpty();
    }
}
