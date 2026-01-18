package com.pomdetom.notes.community.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomdetom.notes.common.model.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Kafka 生产者服务
 */
@Service
@Slf4j
public class KafkaProducerService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private ObjectMapper objectMapper;

    private static final String TOPIC = "message-notify-topic";

    /**
     * 发送消息事件
     *
     * @param message 消息实体
     */
    public void sendMessageEvent(Message message) {
        try {
            String messageJson = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(TOPIC, messageJson);
            log.info("Kafka消息发送成功: {}", messageJson);
        } catch (JsonProcessingException e) {
            log.error("Kafka消息序列化失败: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Kafka消息发送失败: {}", e.getMessage());
        }
    }
}
