package com.pomdetom.notes.common.model.base;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer page;  // 当前页码
    private Integer pageSize;  // 每页显示的记录数
    private Integer total;  // 总记录数
}
