package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 笔记收藏实体类
 */
@Data
public class NoteCollect implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 收藏ID
     */
    private Integer collectId;

    /**
     * 笔记ID
     */
    private Integer noteId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
} 