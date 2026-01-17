package com.pomdetom.notes.file.controller;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.vo.upload.ImageVO;
import com.pomdetom.notes.api.file.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传控制器
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    @Resource
    private UploadService uploadService;

    /**
     * 上传图片
     */
    @PostMapping("/upload/image")
    public ApiResponse<ImageVO> uploadImage(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadImage(file);
    }
}
