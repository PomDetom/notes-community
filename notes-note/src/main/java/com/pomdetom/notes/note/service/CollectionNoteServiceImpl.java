package com.pomdetom.notes.note.service;

import com.pomdetom.notes.note.mapper.CollectionNoteMapper;
import com.pomdetom.notes.api.note.CollectionNoteService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DubboService
public class CollectionNoteServiceImpl implements CollectionNoteService {

    @Resource
    private CollectionNoteMapper collectionNoteMapper;

    @Override
    public Set<Integer> findUserCollectedNoteIds(Long userId, List<Integer> noteIds) {
        List<Integer> userCollectedNoteIds
                = collectionNoteMapper.findUserCollectedNoteIds(userId, noteIds);
        return new HashSet<>(userCollectedNoteIds);
    }
}
