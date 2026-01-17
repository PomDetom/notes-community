package com.pomdetom.notes.common.model.dto.statistic;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StatisticQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "page 不能为空")
    @Min(value = 1, message = "page 必须为正整数")
    private Integer page;

    @NotNull(message = "page 不能为空")
    @Min(value = 1, message = "page 必须为正整数")
    private Integer pageSize;
}
