package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * 收藏夹-笔记关联实体类
 */
@Data
public class CollectionNote implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 收藏夹ID（联合主键）
     */
    private Integer collectionId;

    /*
     * 笔记ID（联合主键）
     */
    private Integer noteId;

    /*
     * 创建时间
     */
    private Date createdAt;

    /*
     * 更新时间
     */
    private Date updatedAt;
}
