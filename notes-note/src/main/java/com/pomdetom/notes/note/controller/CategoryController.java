package com.pomdetom.notes.note.controller;

import com.pomdetom.notes.common.model.base.ApiResponse;
import com.pomdetom.notes.common.model.base.EmptyVO;
import com.pomdetom.notes.common.model.dto.category.CreateCategoryBody;
import com.pomdetom.notes.common.model.dto.category.UpdateCategoryBody;
import com.pomdetom.notes.common.model.vo.category.CategoryVO;
import com.pomdetom.notes.common.model.vo.category.CreateCategoryVO;
import com.pomdetom.notes.api.note.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 获取分类列表（用户端）。
     *
     * @return 包含分类列表的响应。
     */
    @GetMapping("/categories")
    public ApiResponse<List<CategoryVO>> userCategories() {
        return categoryService.categoryList();
    }

    /**
     * 获取分类列表（管理员端）。
     *
     * @return 包含分类列表的响应。
     */
    @GetMapping("/admin/categories")
    public ApiResponse<List<CategoryVO>> categories() {
        return categoryService.categoryList();
    }

    /**
     * 创建新的分类。
     *
     * @param createCategoryBody 包含分类创建信息的请求体。
     * @return 包含创建成功的分类信息的响应。
     */
    @PostMapping("/admin/categories")
    public ApiResponse<CreateCategoryVO> createCategory(
            @Valid @RequestBody CreateCategoryBody createCategoryBody) {
        return categoryService.createCategory(createCategoryBody);
    }

    /**
     * 更新指定的分类信息。
     *
     * @param categoryId 分类ID，必须为正整数。
     * @param updateCategoryBody 包含更新信息的请求体。
     * @return 包含更新操作结果的响应。
     */
    @PatchMapping("/admin/categories/{categoryId}")
    public ApiResponse<EmptyVO> updateCategory(
            @Min(value = 1, message = "categoryId 必须为正整数") @PathVariable Integer categoryId,
            @Valid @RequestBody UpdateCategoryBody updateCategoryBody) {
        return categoryService.updateCategory(categoryId, updateCategoryBody);
    }

    /**
     * 删除指定的分类。
     *
     * @param categoryId 分类ID，必须为正整数。
     * @return 包含删除操作结果的响应。
     */
    @DeleteMapping("/admin/categories/{categoryId}")
    public ApiResponse<EmptyVO> deleteCategory(
            @Min(value = 1, message = "categoryId 必须为正整数") @PathVariable Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
