package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

/**
 * 题单-题目关联实体类
 */
@Data
public class QuestionListItem implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 题单ID（联合主键）
     */
    private Integer questionListId;

    /*
     * 题目ID（联合主键）
     */
    private Integer questionId;

    /*
     * 题单内题目的顺序，从1开始
     */
    private Integer rank;

    /*
     * 创建时间
     */
    private Date createdAt;

    /*
     * 更新时间
     */
    private Date updatedAt;
}
