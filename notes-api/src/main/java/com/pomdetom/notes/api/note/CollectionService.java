package com.pomdetom.notes.api.note;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.collection.CollectionQueryParams;
import com.pomdetom.notes.common.model.dto.collection.CreateCollectionBody;
import com.pomdetom.notes.common.model.dto.collection.UpdateCollectionBody;
import com.pomdetom.notes.common.model.vo.collection.CollectionVO;
import com.pomdetom.notes.common.model.vo.collection.CreateCollectionVO;

import java.util.List;

public interface CollectionService {
    /**
     * 获取收藏夹列表
     *
     * @param queryParams 收藏夹查询参数
     * @return 收藏夹列表
     */
    ApiResponse<List<CollectionVO>> getCollections(CollectionQueryParams queryParams);

    /**
     * 创建收藏夹
     *
     * @param requestBody 收藏夹信息
     * @return 收藏夹信息，包含收藏夹 Id
     */
    ApiResponse<CreateCollectionVO> createCollection(CreateCollectionBody requestBody);

    /**
     * 删除收藏夹
     * @param collectionId 收藏夹 Id
     * @return 占位
     */
    ApiResponse<EmptyVO> deleteCollection(Integer collectionId);

    /**
     * 批量收藏或者取消收藏笔记
     * @param requestBody 包含收藏夹 Id 和笔记 Id 的对象
     *                    action 为 create 时为收藏，为 delete 时为取消收藏
     * @return 占位
     */
    ApiResponse<EmptyVO> batchModifyCollection(UpdateCollectionBody requestBody);
}
