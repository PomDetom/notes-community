package com.pomdetom.notes.common.model.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserActionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Boolean isLiked;
}