package com.pomdetom.notes.common.model.dto.questionListItem;

import java.io.Serializable;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class QuestionListItemQueryParams implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "questionListId 不能为空")
    @Min(value = 1, message = "questionListId 必须为正整数")
    private Integer questionListId;

    @NotNull(message = "page 不能为空")
    @Min(value = 1, message = "page 必须为正整数")
    private Integer page;

    @NotNull(message = "pageSize 不能为空")
    @Range(min = 1, max = 100, message = "pageSize 必须为 1 到 100 之间的整数")
    private Integer pageSize;
}
