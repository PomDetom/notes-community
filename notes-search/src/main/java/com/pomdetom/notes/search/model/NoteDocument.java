package com.pomdetom.notes.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 笔记搜索文档实体
 */
@Data
@Document(indexName = "notes")
public class NoteDocument implements Serializable {

    @Id
    private Integer noteId;

    @Field(type = FieldType.Long)
    private Long authorId;

    @Field(type = FieldType.Integer)
    private Integer questionId;

    /**
     * 笔记内容，使用 ik_max_word 分词器
     */
    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;

    /**
     * 我们假设问题标题也需要被搜索，因此这里冗余存储一下，或者仅存内容
     * 根据 NoteServiceImpl 的下载逻辑，以及 searchNotesByTag 逻辑，搜索的是 content。
     * 但通常搜索功能也会搜索标题。Note 实体没有 title，而是关联 Question 的 title。
     * 如果要搜索标题，需要 sync 的时候查出来 Question 设置进去。
     * 暂时为了简化，只搜索 content，后续 sync 时再优化关联查询。
     */

    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
