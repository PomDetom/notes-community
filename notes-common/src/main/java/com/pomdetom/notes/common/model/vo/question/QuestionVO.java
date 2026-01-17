package com.pomdetom.notes.common.model.vo.question;

import java.io.Serializable;

import lombok.Data;

import java.time.LocalDateTime;

// 用于管理员批量查询题目
@Data
public class QuestionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer questionId;
    private Integer categoryId;
    private String title;
    private Integer difficulty;
    private String examPoint;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
