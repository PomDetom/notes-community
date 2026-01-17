package com.pomdetom.notes.common.model.message;

import java.io.Serializable;

import lombok.Data;

import java.util.List;

@Data
public class ReadMessageBatchRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Integer> messageIds;
}
