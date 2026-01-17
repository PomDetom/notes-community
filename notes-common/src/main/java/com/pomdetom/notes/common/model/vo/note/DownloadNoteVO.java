package com.pomdetom.notes.common.model.vo.note;

import java.io.Serializable;

import lombok.Data;

@Data
public class DownloadNoteVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String markdown;
}
