package com.pomdetom.notes.common.model.vo.category;

import java.io.Serializable;

import lombok.Data;

import java.util.List;

@Data
public class CategoryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer categoryId;
    private String name;
    private Integer parentCategoryId;
    private List<ChildrenCategoryVO> children;

    @Data
    public static class ChildrenCategoryVO {
        private Integer categoryId;
        private String name;
        private Integer parentCategoryId;
    }
}
