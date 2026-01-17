package com.pomdetom.notes.common.model.vo.question;

import java.io.Serializable;

import lombok.Data;

@Data
public class SimpleQuestionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer questionId;
    private String title;
}