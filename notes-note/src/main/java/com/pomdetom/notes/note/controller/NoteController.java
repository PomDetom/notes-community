package com.pomdetom.notes.note.controller;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.note.CreateNoteRequest;
import com.pomdetom.notes.common.model.dto.note.NoteQueryParams;
import com.pomdetom.notes.common.model.dto.note.UpdateNoteRequest;
import com.pomdetom.notes.common.model.vo.note.*;
import com.pomdetom.notes.api.note.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * 笔记控制器
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class NoteController {

    // 自动注入 NoteService 实例，用于处理笔记相关的业务逻辑
    @DubboReference
    private NoteService noteService;

    /**
     * 查询笔记列表
     *
     * @param params 查询参数对象，包含筛选条件
     * @return 返回一个包含笔记列表的 ApiResponse 对象
     */
    @GetMapping("/notes")
    public ApiResponse<List<NoteVO>> getNotes(
            @Valid NoteQueryParams params) {
        return noteService.getNotes(params);
    }

    /**
     * 发布笔记
     *
     * @param request 创建笔记的请求对象，包含笔记的内容等信息
     * @return 返回一个包含新创建笔记信息的 ApiResponse 对象
     */
    @PostMapping("/notes")
    public ApiResponse<CreateNoteVO> createNote(
            @Valid @RequestBody CreateNoteRequest request) {
        return noteService.createNote(request);
    }

    /**
     * 更新笔记
     *
     * @param noteId  笔记的唯一标识符，用于定位要更新的笔记
     * @param request 更新笔记的请求对象，包含需要修改的信息
     * @return 返回一个包含更新后笔记信息的 ApiResponse 对象
     */
    @PatchMapping("/notes/{noteId}")
    public ApiResponse<EmptyVO> updateNote(
            @Min(value = 1, message = "noteId 必须为正整数") @PathVariable Integer noteId,
            @Valid @RequestBody UpdateNoteRequest request) {
        return noteService.updateNote(noteId, request);
    }

    /**
     * 删除笔记
     *
     * @param noteId 笔记的唯一标识符，用于定位要删除的笔记
     * @return 返回一个包含删除结果信息的 ApiResponse 对象
     */
    @DeleteMapping("/notes/{noteId}")
    public ApiResponse<EmptyVO> deleteNote(
            @Min(value = 1, message = "noteId 必须为正整数")
            @PathVariable Integer noteId) {
        return noteService.deleteNote(noteId);
    }

    /**
     * 下载笔记
     * @return
     */
    @GetMapping("/notes/download")
    public ApiResponse<DownloadNoteVO> downloadNote() {
        return noteService.downloadNote();
    }

    /**
     * 提交笔记排行榜
     */
    @GetMapping("/notes/ranklist")
    public ApiResponse<List<NoteRankListItem>> submitNoteRank() {
        return noteService.submitNoteRank();
    }

    /**
     * 用户提交热力图
     */
    @GetMapping("/notes/heatmap")
    public ApiResponse<List<NoteHeatMapItem>> submitNoteHeatMap() {
        return noteService.submitNoteHeatMap();
    }

    /**
     * 用户提交 top3 count
     */
    @GetMapping("/notes/top3count")
    public ApiResponse<Top3Count> submitNoteTop3Count() {
        return noteService.submitNoteTop3Count();
    }
}
