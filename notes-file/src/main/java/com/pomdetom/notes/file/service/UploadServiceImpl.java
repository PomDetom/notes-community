package com.pomdetom.notes.file.service;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.vo.upload.ImageVO;
import com.pomdetom.notes.api.file.FileService;
import com.pomdetom.notes.api.file.UploadService;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@DubboService
public class UploadServiceImpl implements UploadService {

    @Resource
    FileService fileService;

    @Override
    public ApiResponse<ImageVO> uploadImage(MultipartFile file) {
        String url = fileService.uploadImage(file);
        ImageVO imageVO = new ImageVO();
        imageVO.setUrl(url);
        return ApiResponseUtil.success("上传成功", imageVO);
    }
}