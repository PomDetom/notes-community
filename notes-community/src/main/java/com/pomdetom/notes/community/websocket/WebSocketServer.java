package com.pomdetom.notes.community.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket 服务端
 */
@Component
@Slf4j
@ServerEndpoint("/ws/message/{userId}")
public class WebSocketServer {

    /**
     * 记录当前在线连接数
     */
    private static final ConcurrentHashMap<Long, Session> SESSIONS = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        if (SESSIONS.containsKey(userId)) {
            SESSIONS.remove(userId);
            SESSIONS.put(userId, session);
        } else {
            SESSIONS.put(userId, session);
        }
        log.info("用户连接: {}, 当前在线人数: {}", userId, SESSIONS.size());
    }

    @OnClose
    public void onClose(@PathParam("userId") Long userId) {
        if (SESSIONS.containsKey(userId)) {
            SESSIONS.remove(userId);
            log.info("用户退出: {}, 当前在线人数: {}", userId, SESSIONS.size());
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("用户错误: {}, 原因: {}", session.getId(), throwable.getMessage());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到消息: {}, 用户: {}", message, session.getId());
    }

    /**
     * 发送消息
     *
     * @param userId  用户ID
     * @param message 消息内容
     */
    public void sendMessage(Long userId, String message) {
        Session session = SESSIONS.get(userId);
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
                log.info("发送消息给用户: {}, 内容: {}", userId, message);
            } catch (IOException e) {
                log.error("发送消息失败: {}, {}", userId, e.getMessage());
            }
        }
    }

}
