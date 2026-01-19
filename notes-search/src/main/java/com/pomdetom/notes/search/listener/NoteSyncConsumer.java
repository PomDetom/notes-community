package com.pomdetom.notes.search.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pomdetom.notes.common.model.entity.Note;
import com.pomdetom.notes.search.model.NoteDocument;
import com.pomdetom.notes.search.repository.NoteSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 笔记数据同步消费者
 */
@Component
@Slf4j
public class NoteSyncConsumer {

    @Resource
    private NoteSearchRepository noteSearchRepository;

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 监听笔记更新事件（新增/修改）
     *
     * @param record 笔记JSON
     */
    @KafkaListener(topics = "note-sync-es", groupId = "notes-search-group")
    public void consumeNoteSync(String record) {
        try {
            log.info("ES同步 - 收到消息同步请求: {}", record);

            // 简单起见，这里假设 record 就是 Note 实体的 JSON
            // 实际生产中可能包含 operationType (CREATE, UPDATE, DELETE)
            // 这里为了简化，我们约定：
            // 如果是 DELETE 操作，建议单独一个 Topic 或者 消息体带标志。
            // 鉴于 User Request 是 "性能低下" -> "引入 ES"，我们先实现最核心的 Sync。
            // 假设 record 是 Note JSON，直接 save (insert/update)

            // 如果需要处理删除，可以在 Message 中加一个字段。
            // 或者判断 record 格式。
            // 让我们在 NoteServiceImpl 发送的时候做一个简单的封装，或者就发 Note。
            // 既然是 Sync，我们先默认是 Upsert。对于 Delete，我们可能需要另一个监听或者约定。

            Note note = objectMapper.readValue(record, Note.class);

            NoteDocument document = new NoteDocument();
            BeanUtils.copyProperties(note, document);

            noteSearchRepository.save(document);
            log.info("ES同步 - 笔记保存成功, id: {}", document.getNoteId());

        } catch (Exception e) {
            log.error("ES同步 - 消费失败: {}", e.getMessage());
        }
    }

    /**
     * 监听笔记删除事件
     * Topic: note-delete-es
     */
    @KafkaListener(topics = "note-delete-es", groupId = "notes-search-group")
    public void consumeNoteDelete(String noteIdStr) {
        try {
            log.info("ES同步 - 收到删除请求: {}", noteIdStr);
            Integer noteId = Integer.valueOf(noteIdStr);
            noteSearchRepository.deleteById(noteId);
            log.info("ES同步 - 笔记删除成功, id: {}", noteId);
        } catch (Exception e) {
            log.error("ES同步 - 删除失败: {}", e.getMessage());
        }
    }
}
