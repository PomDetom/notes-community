package com.pomdetom.notes.search.repository;

import com.pomdetom.notes.search.model.NoteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 笔记搜索仓库
 */
@Repository
public interface NoteSearchRepository extends ElasticsearchRepository<NoteDocument, Integer> {

    /**
     * 根据内容模糊搜索
     *
     * @param content  关键词
     * @param pageable 分页信息
     * @return 搜索结果
     */
    Page<NoteDocument> findByContentMatches(String content, Pageable pageable);
}
