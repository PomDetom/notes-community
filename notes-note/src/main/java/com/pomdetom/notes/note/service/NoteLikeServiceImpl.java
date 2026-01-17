package com.pomdetom.notes.note.service;

import com.pomdetom.notes.common.annotation.NeedLogin;
import com.pomdetom.notes.common.context.UserContext;
import com.pomdetom.notes.note.mapper.NoteLikeMapper;
import com.pomdetom.notes.note.mapper.NoteMapper;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.message.MessageDTO;
import com.pomdetom.notes.common.model.entity.Note;
import com.pomdetom.notes.common.model.entity.NoteLike;
import com.pomdetom.notes.common.model.enums.message.MessageTargetType;
import com.pomdetom.notes.common.model.enums.message.MessageType;
import com.pomdetom.notes.api.community.MessageService;
import com.pomdetom.notes.api.note.NoteLikeService;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DubboService
@RequiredArgsConstructor
public class NoteLikeServiceImpl implements NoteLikeService {

    @Resource
    private final NoteLikeMapper noteLikeMapper;

    @Resource
    private final NoteMapper noteMapper;

    @DubboReference
    private final MessageService messageService;

    @Override
    @NeedLogin
    @Transactional
    public ApiResponse<EmptyVO> likeNote(Integer noteId) {
        Long userId = UserContext.getUserId();

        // 查询笔记
        Note note = noteMapper.findById(noteId);
        if (note == null) {
            return ApiResponseUtil.error("笔记不存在");
        }

        try {
            // 创建点赞记录
            NoteLike noteLike = new NoteLike();
            noteLike.setNoteId(noteId);
            noteLike.setUserId(userId);
            noteLike.setCreatedAt(new Date());
            noteLikeMapper.insert(noteLike);

            // 增加笔记点赞数
            noteMapper.likeNote(noteId);

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setType(MessageType.LIKE);
            messageDTO.setReceiverId(note.getAuthorId());
            messageDTO.setSenderId(userId);

            messageDTO.setTargetType(MessageTargetType.NOTE);
            messageDTO.setTargetId(noteId);
            messageDTO.setContent(note.getContent());
            messageDTO.setIsRead(false);

            System.out.println(messageDTO);

            messageService.createMessage(messageDTO);

            return ApiResponseUtil.success("点赞成功");
        } catch (Exception e) {
            return ApiResponseUtil.error("点赞失败");
        }
    }

    @Override
    @NeedLogin
    @Transactional
    public ApiResponse<EmptyVO> unlikeNote(Integer noteId) {
        Long userId = UserContext.getUserId();

        // 查询笔记
        Note note = noteMapper.findById(noteId);
        if (note == null) {
            return ApiResponseUtil.error("笔记不存在");
        }

        try {
            // 删除点赞记录
            NoteLike noteLike = noteLikeMapper.findByUserIdAndNoteId(userId, noteId);
            if (noteLike != null) {
                noteLikeMapper.delete(noteLike);
                // 减少笔记点赞数
                noteMapper.unlikeNote(noteId);
            }
            return ApiResponseUtil.success("取消点赞成功");
        } catch (Exception e) {
            return ApiResponseUtil.error("取消点赞失败");
        }
    }

    @Override
    public Set<Integer> findUserLikedNoteIds(Long userId, List<Integer> noteIds) {
        List<Integer> likedIds = noteLikeMapper.findUserLikedNoteIds(userId, noteIds);
        return new HashSet<>(likedIds);
    }
}
