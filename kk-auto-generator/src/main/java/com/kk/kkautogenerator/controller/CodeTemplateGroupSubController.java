package com.kk.kkautogenerator.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
@Api(value = "/kkautogenerator/api/v1/code_template_group_sub", tags = "代码模板分组明细")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/code_template_group_sub")
public class CodeTemplateGroupSubController {

    @Resource
    public ICodeTemplateGroupSubService codeTemplateGroupSubService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/get_code_template_group_sub_page_list")
    public ApiResult<PageResult<CodeTemplateGroupSubListDto>> getCodeTemplateGroupSubPageList(@Valid @RequestBody CodeTemplateGroupSubListVo vo) {

        return new ApiResult(codeTemplateGroupSubService.selectPageList(vo));

    }

    @ApiOperation("删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody CodeTemplateGroupSubDeleteVo vo) {
        codeTemplateGroupSubService.deleteById(vo);
        return ApiResult.SUCCESS;

    }

    @ApiOperation("插入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody CodeTemplateGroupSubAddVo vo) {
        codeTemplateGroupSubService.insert(vo);
        return ApiResult.SUCCESS;

    }

    @ApiOperation("更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody CodeTemplateGroupSubEditVo vo) {
        codeTemplateGroupSubService.update(vo);
        return ApiResult.SUCCESS;

    }

    @ApiOperation("查询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/get_details")
    public ApiResult<CodeTemplateGroupSubDto> getDetails(@Valid @RequestBody CodeTemplateGroupSubDetailsVo vo) {

        return new ApiResult(codeTemplateGroupSubService.selectById(vo));

    }


}
