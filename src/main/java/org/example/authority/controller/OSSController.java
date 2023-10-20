package org.example.authority.controller;

import org.example.authority.service.FileService;
import org.example.authority.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author bingo
 * @description 功能描述
 * @date 2022-11-08
 */
@RestController
@RequestMapping("/api/oss/file")
public class OSSController {
    @Resource
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param module
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file, String module){
        //返回上传到oss的路径
        String url = fileService.upload(file,module);
        return Result.ok(url).message("文件上传成功");
    }
}
