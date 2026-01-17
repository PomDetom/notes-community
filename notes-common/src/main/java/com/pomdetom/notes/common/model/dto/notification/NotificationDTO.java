package com.pomdetom.notes.common.model.dto.notification;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = "content 不能为空")
    @NotNull(message = "content 不能为空")
    private String content;
}
