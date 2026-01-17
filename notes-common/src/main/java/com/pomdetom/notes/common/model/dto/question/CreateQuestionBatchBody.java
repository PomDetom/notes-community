package com.pomdetom.notes.common.model.dto.question;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateQuestionBatchBody implements Serializable {
    private static final long serialVersionUID = 1L;
    private String markdown;
}
