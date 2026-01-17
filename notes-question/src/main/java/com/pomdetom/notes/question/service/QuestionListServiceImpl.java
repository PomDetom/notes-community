package com.pomdetom.notes.question.service;

import com.pomdetom.notes.question.mapper.QuestionListItemMapper;
import com.pomdetom.notes.question.mapper.QuestionListMapper;
import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.questionList.CreateQuestionListBody;
import com.pomdetom.notes.common.model.dto.questionList.UpdateQuestionListBody;
import com.pomdetom.notes.common.model.entity.QuestionList;
import com.pomdetom.notes.common.model.vo.questionList.CreateQuestionListVO;
import com.pomdetom.notes.api.question.QuestionListService;
import com.pomdetom.notes.common.utils.ApiResponseUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class QuestionListServiceImpl implements QuestionListService {

    @Resource
    private QuestionListMapper questionListMapper;

    @Resource
    private QuestionListItemMapper questionListItemMapper;

    @Override
    public ApiResponse<QuestionList> getQuestionList(Integer questionListId) {
        return ApiResponseUtil.success("获取题单成功", questionListMapper.findById(questionListId));
    }

    @Override
    public ApiResponse<List<QuestionList>> getQuestionLists() {
        return ApiResponseUtil.success("获取题单成功", questionListMapper.findAll());
    }

    @Override
    public ApiResponse<CreateQuestionListVO> createQuestionList(CreateQuestionListBody body) {

        QuestionList questionList = new QuestionList();
        BeanUtils.copyProperties(body, questionList);

        // 创建题单
        try {
            questionListMapper.insert(questionList);
            CreateQuestionListVO questionListVO = new CreateQuestionListVO();
            questionListVO.setQuestionListId(questionList.getQuestionListId());
            return ApiResponseUtil.success("创建题单成功", questionListVO);
        } catch (Exception e) {
            return ApiResponseUtil.error("创建题单失败");
        }
    }

    @Override
    public ApiResponse<EmptyVO> deleteQuestionList(Integer questionListId) {
        // 删除题单，还需要删除题单对应的题单项目
        QuestionList questionList = questionListMapper.findById(questionListId);

        if (questionList == null) {
            return ApiResponseUtil.error("题单不存在");
        }

        try {
            questionListMapper.deleteById(questionListId);
            // 删除题单对应的所有题单项
            questionListItemMapper.deleteByQuestionListId(questionListId);
            return ApiResponseUtil.success("删除题单成功");
        } catch (Exception e) {
            return ApiResponseUtil.error("删除题单失败");
        }
    }

    @Override
    public ApiResponse<EmptyVO> updateQuestionList(Integer questionListId, UpdateQuestionListBody body) {

        QuestionList questionList = new QuestionList();
        BeanUtils.copyProperties(body, questionList);
        questionList.setQuestionListId(questionListId);

        System.out.println(questionList);

        try {
            questionListMapper.update(questionList);
            return ApiResponseUtil.success("更新题单成功");
        } catch (Exception e) {
            return ApiResponseUtil.error("更新题单失败");
        }
    }
}
