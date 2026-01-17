package com.pomdetom.notes.common.model.vo.note;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateNoteVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer noteId;
}
