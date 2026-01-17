package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * 记点赞关联实体类
 */
@Data
public class NoteLike implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 笔记ID（联合主键）
     */
    private Integer noteId;

    /*
     * 点赞用户ID（联合主键）
     */
    private Long userId;

    /*
     * 创建时间
     */
    private Date createdAt;

    /*
     * 更新时间
     */
    private Date updatedAt;
}
