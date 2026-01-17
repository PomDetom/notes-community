package com.pomdetom.notes.common.model.vo.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
}
