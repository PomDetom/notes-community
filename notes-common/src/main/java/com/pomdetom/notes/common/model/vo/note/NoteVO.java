package com.pomdetom.notes.common.model.vo.note;

import java.io.Serializable;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteVO implements Serializable {
    private Integer noteId;
    private String content;
    private Boolean needCollapsed = false;
    private String displayContent;
    private Integer likeCount;
    private Integer commentCount;
    private Integer collectCount;
    private LocalDateTime createdAt;
    private SimpleAuthorVO author;
    private UserActionsVO userActions;
    private SimpleQuestionVO question;

    @Data
    public static class SimpleAuthorVO implements Serializable {
        private Long userId;
        private String username;
        private String avatarUrl;
    }

    @Data
    public static class UserActionsVO implements Serializable {
        private Boolean isLiked = false;
        private Boolean isCollected = false;
    }

    @Data
    public static class SimpleQuestionVO implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer questionId;
        private String title;
    }
}
