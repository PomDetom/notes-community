package com.pomdetom.notes.test.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * 题单实体类
 */
@Data
public class QuestionList {
    /*
     * 题单ID（主键）
     */
    private Integer questionListId;

    /*
     * 题单名称
     */
    private String name;

    /**
     * 题单类型
     */
    private Integer type;

    /*
     * 题单描述
     */
    private String description;

    /*
     * 创建时间
     */
    private Date createdAt;

    /*
     * 更新时间
     */
    private Date updatedAt;
}
