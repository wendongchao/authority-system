package org.example.authority.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-08
 */
public interface FileService {

    /**
     * 文件上传
     * @param file 文件上传对象
     * @param module 文件夹名称
     * @return
     */
    String upload(MultipartFile file, String module);

    /**
     * 删除文件
     * @param url
     */
    void deleteFile(String url);
}
