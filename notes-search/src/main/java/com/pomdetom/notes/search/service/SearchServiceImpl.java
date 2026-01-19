package com.pomdetom.notes.search.service;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.entity.Note;
import com.pomdetom.notes.common.model.entity.User;
import com.pomdetom.notes.api.search.SearchService;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import com.pomdetom.notes.common.utils.SearchUtils;
import com.pomdetom.notes.search.model.NoteDocument;
import com.pomdetom.notes.search.repository.NoteSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    // @Autowired
    // private NoteMapper noteMapper;

    // @Autowired
    // private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final String NOTE_SEARCH_CACHE_KEY = "search:note:%s:%d:%d";
    private static final String USER_SEARCH_CACHE_KEY = "search:user:%s:%d:%d";
    private static final String NOTE_TAG_SEARCH_CACHE_KEY = "search:note:tag:%s:%s:%d:%d";
    private static final long CACHE_EXPIRE_TIME = 30; // 分钟

    @Resource
    private NoteSearchRepository noteSearchRepository;

    @Override
    public ApiResponse<List<Note>> searchNotes(String keyword, int page, int pageSize) {
        try {
            // 页码 ES 是从 0 开始
            Pageable pageable = org.springframework.data.domain.PageRequest.of(page - 1,
                    pageSize);

            // 使用 ES 搜索
            Page<NoteDocument> searchResult = noteSearchRepository
                    .findByContentMatches(keyword, pageable);

            List<Note> notes = searchResult.getContent().stream().map(doc -> {
                Note note = new Note();
                org.springframework.beans.BeanUtils.copyProperties(doc, note);
                return note;
            }).collect(java.util.stream.Collectors.toList());

            return ApiResponseUtil.success("搜索成功", notes);
        } catch (Exception e) {
            log.error("搜索笔记失败", e);
            return ApiResponseUtil.error("搜索失败");
        }
    }

    @Override
    public ApiResponse<List<User>> searchUsers(String keyword, int page, int pageSize) {
        // TODO: Implement User search with ES or fix Mapper dependency
        return ApiResponseUtil.success("搜索功能维护中", List.of());
        /*
         * try {
         * String cacheKey = String.format(USER_SEARCH_CACHE_KEY, keyword, page,
         * pageSize);
         * 
         * // 尝试从缓存获取
         * List<User> cachedResult = (List<User>)
         * redisTemplate.opsForValue().get(cacheKey);
         * if (cachedResult != null) {
         * return ApiResponseUtil.success("搜索成功", cachedResult);
         * }
         * 
         * // 计算偏移量
         * int offset = (page - 1) * pageSize;
         * 
         * // 执行搜索
         * List<User> users = userMapper.searchUsers(keyword, pageSize, offset);
         * 
         * // 存入缓存
         * redisTemplate.opsForValue().set(cacheKey, users, CACHE_EXPIRE_TIME,
         * TimeUnit.MINUTES);
         * 
         * return ApiResponseUtil.success("搜索成功", users);
         * } catch (Exception e) {
         * log.error("搜索用户失败", e);
         * return ApiResponseUtil.error("搜索失败");
         * }
         */
    }

    @Override
    public ApiResponse<List<Note>> searchNotesByTag(String keyword, String tag, int page, int pageSize) {
        // TODO: Implement Tag search with ES or fix Mapper dependency
        return ApiResponseUtil.success("搜索功能维护中", List.of());
        /*
         * try {
         * String cacheKey = String.format(NOTE_TAG_SEARCH_CACHE_KEY, keyword, tag,
         * page, pageSize);
         * 
         * // 尝试从缓存获取
         * List<Note> cachedResult = (List<Note>)
         * redisTemplate.opsForValue().get(cacheKey);
         * if (cachedResult != null) {
         * return ApiResponseUtil.success("搜索成功", cachedResult);
         * }
         * 
         * // 处理关键词
         * keyword = SearchUtils.preprocessKeyword(keyword);
         * 
         * // 计算偏移量
         * int offset = (page - 1) * pageSize;
         * 
         * // 执行搜索
         * List<Note> notes = noteMapper.searchNotesByTag(keyword, tag, pageSize,
         * offset);
         * 
         * // 存入缓存
         * redisTemplate.opsForValue().set(cacheKey, notes, CACHE_EXPIRE_TIME,
         * TimeUnit.MINUTES);
         * 
         * return ApiResponseUtil.success("搜索成功", notes);
         * } catch (Exception e) {
         * log.error("搜索笔记失败", e);
         * return ApiResponseUtil.error("搜索失败");
         * }
         */
    }
}
