package com.pomdetom.notes.common.model.vo.note;

import java.io.Serializable;

import lombok.Data;

@Data
public class NoteRankListItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private String username;
    private String avatarUrl;
    private Integer noteCount;
    private Integer rank;
}
