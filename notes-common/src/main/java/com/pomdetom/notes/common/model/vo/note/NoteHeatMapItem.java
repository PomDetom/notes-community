package com.pomdetom.notes.common.model.vo.note;

import java.io.Serializable;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteHeatMapItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate date;
    private Integer count;
    private Integer rank;
}
