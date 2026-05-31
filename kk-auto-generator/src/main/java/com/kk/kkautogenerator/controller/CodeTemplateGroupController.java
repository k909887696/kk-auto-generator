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
import com.kk.kkautogenerator.service.ICodeTemplateGroupService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupEditVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateGroupDeleteVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateGroupDto;
import com.kk.kkautogenerator.dao.entity.CodeTemplateGroup;
/**
 * <p>
 * 代码模板分组 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Tag(name = "代码模板分组")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/code_template_group")
public class CodeTemplateGroupController {

    @Resource
    public ICodeTemplateGroupService codeTemplateGroupService;

    @Operation(summary = "获取分页结果集")
    
    @PostMapping("/get_code_template_group_page_list")
    public ApiResult< PageResult<CodeTemplateGroupListDto> > getCodeTemplateGroupPageList(@Valid @RequestBody CodeTemplateGroupListVo vo)   {

        return new  ApiResult(codeTemplateGroupService.selectPageList(vo));

    }
    @Operation(summary = "删除")
    
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody CodeTemplateGroupDeleteVo vo)   {
        codeTemplateGroupService.deleteById(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "插入")
    
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody CodeTemplateGroupAddVo vo)   {
        codeTemplateGroupService.insert(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "更新")
    
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody CodeTemplateGroupEditVo vo)   {
        codeTemplateGroupService.update(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "查询详情")
    
    @PostMapping("/get_details")
    public ApiResult< CodeTemplateGroupDto > getDetails(@Valid @RequestBody CodeTemplateGroupDetailsVo vo)   {

        return new  ApiResult(codeTemplateGroupService.selectById(vo));

    }



}
