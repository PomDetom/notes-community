package com.pomdetom.notes.community.service;

import com.pomdetom.notes.api.note.NoteService;
import com.pomdetom.notes.api.question.QuestionService;
import com.pomdetom.notes.common.context.UserContext;
import com.pomdetom.notes.community.mapper.MessageMapper;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.message.MessageDTO;
import com.pomdetom.notes.common.model.entity.Message;
import com.pomdetom.notes.common.model.entity.Note;
import com.pomdetom.notes.common.model.entity.Question;
import com.pomdetom.notes.common.model.entity.User;
import com.pomdetom.notes.common.model.enums.message.MessageType;
import com.pomdetom.notes.common.model.vo.message.MessageVO;
import com.pomdetom.notes.api.community.MessageService;
import com.pomdetom.notes.api.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 消息服务实现类
 */
@DubboService
@Slf4j
public class MessageServiceImpl implements MessageService {

    @DubboReference
    private NoteService noteService;

    @DubboReference
    private QuestionService questionService;

    @Resource
    private MessageMapper messageMapper;

    @DubboReference
    private UserService userService;

    @Resource
    private KafkaProducerService kafkaProducerService;

    @Override
    public Integer createMessage(MessageDTO messageDTO) {
        try {
            Message message = new Message();
            BeanUtils.copyProperties(messageDTO, message);

            if (messageDTO.getContent() == null) {
                message.setContent("");
            }

            int result = messageMapper.insert(message);

            // 发送消息事件到 Kafka
            kafkaProducerService.sendMessageEvent(message);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("创建消息通知失败: " + e.getMessage());
        }
    }

    @Override
    public ApiResponse<List<MessageVO>> getMessages() {

        Long currentUserId = UserContext.getUserId();

        // 获取用户所有的消息对象
        List<Message> messages = messageMapper.selectByUserId(currentUserId);

        List<Long> senderIds = messages.stream().map(Message::getSenderId).toList();

        // 将 message 专成 messageVO
        Map<Long, User> userMap = userService.getUserMapByIds(senderIds);

        List<MessageVO> messageVOS = messages.stream().map(message -> {
            MessageVO messageVO = new MessageVO();
            BeanUtils.copyProperties(message, messageVO);

            // 设置发送者信息
            MessageVO.Sender sender = new MessageVO.Sender();
            sender.setUserId(message.getSenderId());
            sender.setUsername(userMap.get(message.getSenderId()).getUsername());
            sender.setAvatarUrl(userMap.get(message.getSenderId()).getAvatarUrl());
            messageVO.setSender(sender);

            // 设置 target 信息
            if (!Objects.equals(message.getType(), MessageType.SYSTEM)) {
                MessageVO.Target target = new MessageVO.Target();
                target.setTargetId(message.getTargetId());
                target.setTargetType(message.getTargetType());

                Note note = noteService.findById(message.getTargetId());

                MessageVO.QuestionSummary questionSummary = new MessageVO.QuestionSummary();
                Question question = questionService.findById(note.getQuestionId());
                questionSummary.setQuestionId(question.getQuestionId());
                questionSummary.setTitle(question.getTitle());
                target.setQuestionSummary(questionSummary);
                messageVO.setTarget(target);
            }

            return messageVO;
        }).toList();

        return ApiResponse.success(messageVOS);
    }

    @Override
    public ApiResponse<EmptyVO> markAsRead(Integer messageId) {
        Long currentUserId = UserContext.getUserId();
        messageMapper.markAsRead(messageId, currentUserId);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<EmptyVO> markAsReadBatch(List<Integer> messageIds) {
        Long currentUserId = UserContext.getUserId();
        messageMapper.markAsReadBatch(messageIds, currentUserId);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<EmptyVO> markAllAsRead() {
        Long currentUserId = UserContext.getUserId();
        messageMapper.markAllAsRead(currentUserId);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<EmptyVO> deleteMessage(Integer messageId) {
        Long currentUserId = UserContext.getUserId();
        messageMapper.deleteMessage(messageId, currentUserId);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse<Integer> getUnreadCount() {
        Long currentUserId = UserContext.getUserId();
        Integer count = messageMapper.countUnread(currentUserId);
        return ApiResponse.success(count);
    }
}
