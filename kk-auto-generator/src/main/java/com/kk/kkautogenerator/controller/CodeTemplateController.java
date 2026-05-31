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
import com.kk.kkautogenerator.service.ICodeTemplateService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.kkautogenerator.model.vo.CodeTemplateListVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateListDto;
import com.kk.kkautogenerator.model.vo.CodeTemplateAddVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateEditVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateDetailsVo;
import com.kk.kkautogenerator.model.vo.CodeTemplateDeleteVo;
import com.kk.kkautogenerator.model.dto.CodeTemplateDto;
import com.kk.kkautogenerator.dao.entity.CodeTemplate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码模板 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Tag(name = "代码模板")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/code_template")
public class CodeTemplateController {

    @Resource
    public ICodeTemplateService codeTemplateService;

    @Operation(summary = "获取分页结果集")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/get_code_template_page_list")
    public ApiResult< PageResult<CodeTemplateListDto> > getCodeTemplatePageList(@Valid @RequestBody CodeTemplateListVo vo)   {

        return new  ApiResult(codeTemplateService.selectPageList(vo));

    }
    @Operation(summary = "删除")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody CodeTemplateDeleteVo vo)   {
        codeTemplateService.deleteById(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "插入")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody CodeTemplateAddVo vo)   {
        codeTemplateService.insert(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "更新")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody CodeTemplateEditVo vo)   {
        codeTemplateService.update(vo);
        return ApiResult.SUCCESS;

    }
    @Operation(summary = "查询详情")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/get_details")
    public ApiResult< CodeTemplateDto > getDetails(@Valid @RequestBody CodeTemplateDetailsVo vo)   {

        return new  ApiResult(codeTemplateService.selectById(vo));

    }

    @Operation(summary = "根据模板类型分组返回")
    @Parameters({@Parameter(name = "token", description = "身份令牌", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "signature", description = "签名", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "timestamp", description = "时间戳", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "source", description = "来源（app/web/minotor）", in = ParameterIn.HEADER, required = false)
            , @Parameter(name = "version", description = "版本号（1.0.0）", in = ParameterIn.HEADER)})
    @PostMapping("/get_code_template_group_by_type")
    public ApiResult<Map<String, List<CodeTemplateListDto>>> getPageList4GroupByType(@Valid @RequestBody CodeTemplateListVo vo)   {

        return new  ApiResult(codeTemplateService.selectPageList4GroupByType(vo));

    }

}
