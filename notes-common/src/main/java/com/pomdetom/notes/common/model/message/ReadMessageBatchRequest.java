package com.pomdetom.notes.common.model.message;

import lombok.Data;

import java.util.List;

@Data
public class ReadMessageBatchRequest {
    private List<Integer> messageIds;
}
