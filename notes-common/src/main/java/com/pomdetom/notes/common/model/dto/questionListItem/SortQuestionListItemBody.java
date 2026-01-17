package com.pomdetom.notes.common.model.dto.questionListItem;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SortQuestionListItemBody implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "questionListId 不能为空")
    @Min(value = 1, message = "questionListId 必须为正整数")
    private Integer questionListId;

    @NotNull(message = "questionListItemIds 不能为空")
    private List<Integer> questionIds;
}
