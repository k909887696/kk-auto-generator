package com.kk.kkautogenerator.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

import com.kk.kkautogenerator.service.ICodeTemplateGroupSubService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubEditVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupSubDeleteVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupSubDto;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroupSub;

/**
 * <p>
 * 代码模板分组明细 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Tag(name = "代码模板分组明细")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/code_template_group_sub")
public class CodeTemplateGroupSubController {

    @Resource
    public ICodeTemplateGroupSubService codeTemplateGroupSubService;

    @Operation(summary = "获取分页结果集")

    @PostMapping("/get_code_template_group_sub_page_list")
    public ApiResult<PageResult<CodeTemplateGroupSubListDto>> getCodeTemplateGroupSubPageList(@Valid @RequestBody CodeTemplateGroupSubListVo vo) {

        return new ApiResult(codeTemplateGroupSubService.selectPageList(vo));

    }

    @Operation(summary = "删除")
    
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody CodeTemplateGroupSubDeleteVo vo) {
        codeTemplateGroupSubService.deleteById(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "插入")
    
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody CodeTemplateGroupSubAddVo vo) {
        codeTemplateGroupSubService.insert(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "更新")
    
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody CodeTemplateGroupSubEditVo vo) {
        codeTemplateGroupSubService.update(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "查询详情")
    
    @PostMapping("/get_details")
    public ApiResult<CodeTemplateGroupSubDto> getDetails(@Valid @RequestBody CodeTemplateGroupSubDetailsVo vo) {

        return new ApiResult(codeTemplateGroupSubService.selectById(vo));

    }


}
