package com.pomdetom.notes.common.model.base;

import java.io.Serializable;

import lombok.Data;

/**
 * 空响应类，用于表示无数据返回的情况
 */
@Data
public class EmptyVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean empty = true;
}
