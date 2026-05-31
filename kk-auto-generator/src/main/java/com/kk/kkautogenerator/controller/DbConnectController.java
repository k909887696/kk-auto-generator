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

import com.kk.kkautogenerator.service.IDbConnectService;
import com.kk.common.web.model.ApiResult;
import com.kk.common.base.model.PageResult;
import com.kk.kkautogenerator.model.vo.DbConnectListVo;
import com.kk.kkautogenerator.model.dto.DbConnectListDto;
import com.kk.kkautogenerator.model.vo.DbConnectAddVo;
import com.kk.kkautogenerator.model.vo.DbConnectEditVo;
import com.kk.kkautogenerator.model.vo.DbConnectDetailsVo;
import com.kk.kkautogenerator.model.vo.DbConnectDeleteVo;
import com.kk.kkautogenerator.model.dto.DbConnectDto;
import com.kk.kkautogenerator.dao.entity.DbConnect;

/**
 * <p>
 * 数据库链接 前端控制器
 * </p>
 *
 * @author kk
 * @since 2023-08-02
 */
@Tag(name = "数据库链接")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/db_connect")
public class DbConnectController {

    @Resource
    public IDbConnectService dbConnectService;

    @Operation(summary = "获取分页结果集")
    
    @PostMapping("/get_db_connect_page_list")
    public ApiResult<PageResult<DbConnectListDto>> getDbConnectPageList(@Valid @RequestBody DbConnectListVo vo) {

        return new ApiResult(dbConnectService.selectPageList(vo));

    }

    @Operation(summary = "删除")
    
    @PostMapping("/delete_by_id")
    public ApiResult<?> deleteById(@Valid @RequestBody DbConnectDeleteVo vo) {
        dbConnectService.deleteById(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "插入")
    
    @PostMapping("/insert")
    public ApiResult<?> insert(@Valid @RequestBody DbConnectAddVo vo) {
        dbConnectService.insert(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "更新")
    
    @PostMapping("/update")
    public ApiResult<?> update(@Valid @RequestBody DbConnectEditVo vo) {
        dbConnectService.update(vo);
        return ApiResult.SUCCESS;

    }

    @Operation(summary = "查询详情")
    
    @PostMapping("/get_details")
    public ApiResult<DbConnectDto> getDetails(@Valid @RequestBody DbConnectDetailsVo vo) {

        return new ApiResult(dbConnectService.selectById(vo));

    }


}
