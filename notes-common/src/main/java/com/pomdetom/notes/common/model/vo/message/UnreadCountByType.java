package com.pomdetom.notes.common.model.vo.message;

import java.io.Serializable;

import lombok.Data;

/**
 * 各类型未读消息数量
 */
@Data
public class UnreadCountByType implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息类型
     */
    private String type;

    /**
     * 未读数量
     */
    private Integer count;
} 