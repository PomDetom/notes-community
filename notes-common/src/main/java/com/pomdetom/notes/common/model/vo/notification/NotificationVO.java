package com.pomdetom.notes.common.model.vo.notification;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotificationVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;
}
