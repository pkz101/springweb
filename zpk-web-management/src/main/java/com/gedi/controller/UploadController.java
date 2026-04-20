package com.gedi.controller;

import com.gedi.pojo.Result;
import com.gedi.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping
    public Result upload(MultipartFile  file) throws Exception {
        log.info("文件上传开始:{}", file);
        String originalFilename = file.getOriginalFilename();
        String url = aliyunOSSOperator.upload(file.getBytes(), originalFilename);
        log.info("文件上传结束:{}", url);
        return Result.success(url);
    }
}
