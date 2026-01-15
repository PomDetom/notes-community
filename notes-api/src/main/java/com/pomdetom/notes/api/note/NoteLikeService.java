package com.pomdetom.notes.api.note;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;

import java.util.List;
import java.util.Set;

public interface NoteLikeService {

    /**
     * 查询用户点赞过的笔记 ID
     *
     * @param userId  用户 ID
     * @param noteIds 笔记 ID
     * @return 用户点赞的笔记 ID 集合
     */
    Set<Integer> findUserLikedNoteIds(Long userId, List<Integer> noteIds);

    /**
     * 点赞笔记
     *
     * @param noteId 笔记的唯一标识符
     * @return 返回一个包含操作结果的ApiResponse对象
     */
    ApiResponse<EmptyVO> likeNote(Integer noteId);

    /**
     * 取消点赞笔记
     *
     * @param noteId 笔记的唯一标识符
     * @return 返回一个包含操作结果的ApiResponse对象
     */
    ApiResponse<EmptyVO> unlikeNote(Integer noteId);
}
