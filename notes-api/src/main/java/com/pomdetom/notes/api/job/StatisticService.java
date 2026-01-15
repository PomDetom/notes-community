package com.pomdetom.notes.api.job;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.dto.statistic.StatisticQueryParam;
import com.pomdetom.notes.common.model.entity.Statistic;

import java.util.List;

public interface StatisticService {
    /**
     * 获取统计信息
     * @param queryParam 查询参数，用于指定统计条件
     * @return 返回一个ApiResponse对象，其中包含符合查询条件的统计信息列表
     */
    ApiResponse<List<Statistic>> getStatistic(StatisticQueryParam queryParam);
}
