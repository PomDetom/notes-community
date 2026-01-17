package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论点赞实体类
 */
@Data
public class CommentLike implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 评论点赞ID
     */
    private Integer commentLikeId;

    /**
     * 评论ID
     */
    private Integer commentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
} 