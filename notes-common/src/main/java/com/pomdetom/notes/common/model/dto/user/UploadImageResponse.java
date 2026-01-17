package com.pomdetom.notes.common.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 图片上传响应DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 状态码
     */
    private Integer code;

    /*
     * 提示信息
     */
    private String msg;

    /*
     * 响应数据
     */
    private UploadImageResponseData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UploadImageResponseData {
        /*
         * 图片访问URL
         */
        private String url;
    }
}