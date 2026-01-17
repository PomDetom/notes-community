package com.pomdetom.notes.common.model.dto.questionListItem;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateQuestionListItemBody implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "questionListId 不能为空")
    @Min(value = 1, message = "questionListId 必须为正整数")
    private Integer questionListId;

    @NotNull(message = "questionId 不能为空")
    @Min(value = 1, message = "questionId 必须为正整数")
    private Integer questionId;
}
