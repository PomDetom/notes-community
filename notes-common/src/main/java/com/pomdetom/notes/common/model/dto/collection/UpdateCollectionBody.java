package com.pomdetom.notes.common.model.dto.collection;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateCollectionBody implements Serializable {
    private static final long serialVersionUID = 1L;
    @Min(value = 1, message = "noteId 必须为正整数")
    private Integer noteId;

    private UpdateItem[] collections;

    @Data
    public static class UpdateItem {
        @Min(value = 1, message = "collectionId 必须为正整数")
        private Integer collectionId;
        // 必须为 create 或者 delete
        @NotNull(message = "action 不能为空")
        @NotEmpty(message = "action 不能为空")
        @Pattern(regexp = "create|delete", message = "action 必须为 create 或者 delete")
        private String action;
    }
}
