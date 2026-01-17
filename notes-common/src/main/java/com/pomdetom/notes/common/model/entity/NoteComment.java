package com.pomdetom.notes.common.model.entity;

import java.io.Serializable;

import lombok.Data;

import java.util.Date;

@Data
public class NoteComment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer noteId;
    private Long userId;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isDeleted;
} 