package com.pomdetom.notes.common.model.dto.comment;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 更新评论请求
 */
@Data
public class UpdateCommentRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;
} 