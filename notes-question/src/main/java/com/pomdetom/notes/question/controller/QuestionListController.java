package com.pomdetom.notes.question.controller;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.questionList.CreateQuestionListBody;
import com.pomdetom.notes.common.model.dto.questionList.UpdateQuestionListBody;
import com.pomdetom.notes.common.model.entity.QuestionList;
import com.pomdetom.notes.common.model.vo.questionList.CreateQuestionListVO;
import com.pomdetom.notes.api.question.QuestionListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionListController {

    @Resource
    private QuestionListService questionListService;

    /**
     * 获取题单。
     *
     * @return 包含题单列表的响应。
     */
    @GetMapping("/admin/questionlists/{questionListId}")
    public ApiResponse<QuestionList> getQuestionList(@Min(value = 1, message = "questionListId 必须为正整数")
                                                     @PathVariable Integer questionListId) {
        return questionListService.getQuestionList(questionListId);
    }

    /**
     * 获取题单列表。
     *
     * @return 包含题单列表的响应。
     */
    @GetMapping("/admin/questionlists")
    public ApiResponse<List<QuestionList>> getQuestionLists() {
        return questionListService.getQuestionLists();
    }

    /**
     * 创建新的题单。
     *
     * @param body 包含题单创建信息的请求体。
     * @return 包含创建成功的题单信息的响应。
     */
    @PostMapping("/admin/questionlists")
    public ApiResponse<CreateQuestionListVO> createQuestionList(@Valid @RequestBody CreateQuestionListBody body) {
        return questionListService.createQuestionList(body);
    }

    /**
     * 删除指定的题单。
     *
     * @param questionListId 要删除的题单ID，必须为正整数。
     * @return 包含删除操作结果的响应。
     */
    @DeleteMapping("/admin/questionlists/{questionListId}")
    public ApiResponse<EmptyVO> deleteQuestionList(@Min(value = 1, message = "questionListId 必须为正整数")
                                                   @PathVariable Integer questionListId) {
        return questionListService.deleteQuestionList(questionListId);
    }

    /**
     * 更新指定的题单信息。
     *
     * @param questionListId 要更新的题单ID，必须为正整数。
     * @param body           包含更新信息的请求体。
     * @return 包含更新操作结果的响应。
     */
    @PatchMapping("/admin/questionlists/{questionListId}")
    public ApiResponse<EmptyVO> updateQuestionList(@Min(value = 1, message = "questionListId 必须为正整数")
                                                   @PathVariable Integer questionListId,
                                                   @Valid @RequestBody UpdateQuestionListBody body) {
        return questionListService.updateQuestionList(questionListId, body);
    }
}
