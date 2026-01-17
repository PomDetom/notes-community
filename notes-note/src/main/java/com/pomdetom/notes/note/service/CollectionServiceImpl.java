package com.pomdetom.notes.note.service;

import com.pomdetom.notes.common.annotation.NeedLogin;
import com.pomdetom.notes.common.context.UserContext;
import com.pomdetom.notes.note.mapper.CollectionMapper;
import com.pomdetom.notes.note.mapper.CollectionNoteMapper;
import com.pomdetom.notes.note.mapper.NoteMapper;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.collection.CollectionQueryParams;
import com.pomdetom.notes.common.model.dto.collection.CreateCollectionBody;
import com.pomdetom.notes.common.model.dto.collection.UpdateCollectionBody;
import com.pomdetom.notes.common.model.entity.Collection;
import com.pomdetom.notes.common.model.entity.CollectionNote;
import com.pomdetom.notes.common.model.vo.collection.CollectionVO;
import com.pomdetom.notes.common.model.vo.collection.CreateCollectionVO;
import com.pomdetom.notes.api.note.CollectionService;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@DubboService
public class CollectionServiceImpl implements CollectionService {

    @Resource
    private CollectionMapper collectionMapper;

    @Resource
    private CollectionNoteMapper collectionNoteMapper;

    @Resource
    private NoteMapper noteMapper;

    @Override
    public ApiResponse<List<CollectionVO>> getCollections(CollectionQueryParams queryParams) {
        // 收藏夹列表
        List<Collection> collections = collectionMapper.findByCreatorId(queryParams.getCreatorId());
        List<Integer> collectionIds = collections.stream().map(Collection::getCollectionId).toList();
        final Set<Integer> collectedNoteIdCollectionIds;

        // 查看是否传入了 noteId
        if (queryParams.getNoteId() != null) {
            // 收藏了 noteId 的收藏夹列表
            collectedNoteIdCollectionIds = collectionNoteMapper.filterCollectionIdsByNoteId(queryParams.getNoteId(), collectionIds);
        } else {
            collectedNoteIdCollectionIds = Collections.emptySet();
        }

        // 将 collections 映射为 CollectionVOList
        List<CollectionVO> collectionVOList = collections.stream().map(collection -> {
            CollectionVO collectionVO = new CollectionVO();
            BeanUtils.copyProperties(collection, collectionVO);

            // 检查是否传入了 noteId 参数并且当前收藏夹收藏了该 note
            if (queryParams.getNoteId() == null) return collectionVO;

            // 设置收藏夹收藏笔记状态
            CollectionVO.NoteStatus noteStatus = new CollectionVO.NoteStatus();

            noteStatus.setIsCollected(collectedNoteIdCollectionIds.contains(collection.getCollectionId()));
            noteStatus.setNoteId(queryParams.getNoteId());
            collectionVO.setNoteStatus(noteStatus);

            return collectionVO;
        }).toList();

        return ApiResponseUtil.success("获取收藏夹列表成功", collectionVOList);
    }

    @Override
    @NeedLogin
    public ApiResponse<CreateCollectionVO> createCollection(CreateCollectionBody requestBody) {

        Long creatorId = UserContext.getUserId();

        Collection collection = new Collection();
        BeanUtils.copyProperties(requestBody, collection);
        collection.setCreatorId(creatorId);

        try {
            collectionMapper.insert(collection);
            CreateCollectionVO createCollectionVO = new CreateCollectionVO();

            createCollectionVO.setCollectionId(collection.getCollectionId());
            return ApiResponseUtil.success("创建成功", createCollectionVO);
        } catch (Exception e) {
            return ApiResponseUtil.error("创建失败");
        }
    }

    @Override
    @NeedLogin
    @Transactional
    public ApiResponse<EmptyVO> deleteCollection(Integer collectionId) {
        // 校验是否是收藏夹创建者
        Long creatorId = UserContext.getUserId();
        Collection collection = collectionMapper.findByIdAndCreatorId(collectionId, creatorId);

        if (collection == null) {
            return ApiResponseUtil.error("收藏夹不存在或者无权限删除");
        }

        try {
            // 删除收藏夹
            collectionMapper.deleteById(collectionId);
            // 删除收藏夹中的所有的笔记
            collectionNoteMapper.deleteByCollectionId(collectionId);
            return ApiResponseUtil.success("删除成功");
        } catch (Exception e) {
            return ApiResponseUtil.error("删除失败");
        }
    }

    @Override
    @NeedLogin
    @Transactional
    public ApiResponse<EmptyVO> batchModifyCollection(UpdateCollectionBody requestBody) {

        Long userId = UserContext.getUserId();
        Integer noteId = requestBody.getNoteId();

        UpdateCollectionBody.UpdateItem[] collections = requestBody.getCollections();

        for (UpdateCollectionBody.UpdateItem collection : collections) {
            Integer collectionId = collection.getCollectionId();
            String action = collection.getAction();

            // 校验是否是收藏夹创建者
            Collection collectionEntity = collectionMapper.findByIdAndCreatorId(collectionId, userId);

            if (collectionEntity == null) {
                return ApiResponseUtil.error("收藏夹不存在或者无权限操作");
            }

            if ("create".equals(action)) {
                try {
                    // 获取用户是否收藏过该笔记
                    if (collectionMapper.countByCreatorIdAndNoteId(userId, noteId) == 0) {
                        // 笔记不存在，给笔记增加收藏量
                        noteMapper.collectNote(noteId);
                    }
                    CollectionNote collectionNote = new CollectionNote();
                    collectionNote.setCollectionId(collectionId);
                    collectionNote.setNoteId(noteId);
                    collectionNoteMapper.insert(collectionNote);
                } catch (Exception e) {
                    return ApiResponseUtil.error("收藏失败");
                }
            }

            if ("delete".equals(action)) {
                try {
                    collectionNoteMapper.deleteByCollectionIdAndNoteId(collectionId, noteId);
                    if (collectionMapper.countByCreatorIdAndNoteId(userId, noteId) == 0) {
                        // 笔记不存在，给笔记减少收藏量
                        noteMapper.unCollectNote(noteId);
                    }
                } catch (Exception e) {
                    return ApiResponseUtil.error("取消收藏失败");
                }
            }
        }
        return ApiResponseUtil.success("操作成功");
    }
}
