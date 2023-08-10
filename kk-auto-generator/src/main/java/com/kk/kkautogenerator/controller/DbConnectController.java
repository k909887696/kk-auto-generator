package com.kk.kkautogenerator.controller;


import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
@Api(value = "/kkautogenerator/api/v1/db_connect", tags = "数据库链接")
@ResponseBody
@RestController
@RequestMapping("/kkautogenerator/api/v1/db_connect")
public class DbConnectController {

    @Resource
    public IDbConnectService dbConnectService;

    @ApiOperation("获取分页结果集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"),
            @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")
    })
    @PostMapping("/get_db_connect_page_list")
    public ApiResult<PageResult<DbConnectListDto>> getDbConnectPageList(@Valid @RequestBody DbConnectListVo vo) {

        return new ApiResult(dbConnectService.selectPageList(vo));

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
    public ApiResult<?> deleteById(@Valid @RequestBody DbConnectDeleteVo vo) {
        dbConnectService.deleteById(vo);
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
    public ApiResult<?> insert(@Valid @RequestBody DbConnectAddVo vo) {
        dbConnectService.insert(vo);
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
    public ApiResult<?> update(@Valid @RequestBody DbConnectEditVo vo) {
        dbConnectService.update(vo);
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
    public ApiResult<DbConnectDto> getDetails(@Valid @RequestBody DbConnectDetailsVo vo) {

        return new ApiResult(dbConnectService.selectById(vo));

    }


}
