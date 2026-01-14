package com.pomdetom.notes.common.model.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 笔记实体类
 */
@Data
public class Note {
    /**
     * 笔记ID
     */
    private Integer noteId;

    /**
     * 作者ID
     */
    private Long authorId;

    /**
     * 问题ID
     */
    private Integer questionId;

    /**
     * 笔记内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 收藏数
     */
    private Integer collectCount;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
