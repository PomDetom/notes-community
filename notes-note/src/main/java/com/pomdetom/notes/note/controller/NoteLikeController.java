package com.pomdetom.notes.note.controller;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.api.note.NoteLikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class NoteLikeController {

    @Resource
    private NoteLikeService noteLikeService;

    @PostMapping("/like/note/{noteId}")
    public ApiResponse<EmptyVO> likeNote(@PathVariable Integer noteId) {
        return noteLikeService.likeNote(noteId);
    }

    @DeleteMapping("/like/note/{noteId}")
    public ApiResponse<EmptyVO> unlikeNote(@PathVariable Integer noteId) {
        return noteLikeService.unlikeNote(noteId);
    }
}
