package com.pomdetom.notes.community.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomdetom.notes.common.model.entity.Message;
import com.pomdetom.notes.community.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Kafka 消费者服务
 */
@Service
@Slf4j
public class KafkaConsumerService {

    @Resource
    private WebSocketServer webSocketServer;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 消费消息事件，广播模式（每个实例都有唯一的 groupId）
     *
     * @param record 消息记录
     */
    @KafkaListener(topics = "message-notify-topic", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
    public void consumeMessageEvent(String record) {
        try {
            log.info("Kafka监听到消息: {}", record);
            Message message = objectMapper.readValue(record, Message.class);

            // 推送给目标用户（如果连接在本实例上）
            // 这里简单推送一个通知，前端收到后可以去拉取最新消息或者直接显示
            // 为了简单起见，我们推送一个简单的JSON字符串
            String notification = objectMapper.writeValueAsString(message);
            // 使用 receiverId 作为目标用户ID
            webSocketServer.sendMessage(message.getReceiverId(), notification);

        } catch (Exception e) {
            log.error("Kafka消息消费失败: {}", e.getMessage());
        }
    }
}
