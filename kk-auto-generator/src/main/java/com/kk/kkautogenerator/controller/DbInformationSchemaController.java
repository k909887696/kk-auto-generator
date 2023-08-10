package com.kk.kkautogenerator.controller;

import com.kk.common.web.model.ApiResult;
import com.kk.kkautogenerator.model.dto.GenerateCodeDto;
import com.kk.kkautogenerator.model.vo.ColumnsInfoListVo;
import com.kk.kkautogenerator.model.vo.DbDataSource;
import com.kk.kkautogenerator.model.vo.GenerateCodeVo;
import com.kk.kkautogenerator.model.vo.TablesInfoListVo;
import com.kk.kkautogenerator.service.IDbInformationSchemaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "/kkautogenerator/api/v1/db_information_schema", tags = {"数据库系统信息"})
@ResponseBody
@RestController
@RequestMapping({"/kkautogenerator/api/v1/db_information_schema"})
public class DbInformationSchemaController {
    @Resource
    public IDbInformationSchemaService dbInformationSchemaService;

    @ApiOperation("查询数据表信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @PostMapping({"/get_tables_info"})
    public ApiResult<List<Map<String, Object>>> getTablesInfo(@Valid @RequestBody TablesInfoListVo vo) throws Exception {
        List<Map<String, Object>> res = this.dbInformationSchemaService.getTablesInfo(vo);
        return new ApiResult(res);
    }


    @ApiOperation("查询数据表字段信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @PostMapping({"/get_columns_info"})
    public ApiResult<List<Map<String, Object>>> getColumnsInfo(@Valid @RequestBody ColumnsInfoListVo vo) throws Exception {
        List<Map<String, Object>> res = this.dbInformationSchemaService.getColumnsInfo(vo);
        return new ApiResult(res);
    }


    @ApiOperation("测试数据链接")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @PostMapping({"/test_connect"})
    public ApiResult<String> testConnect(@Valid @RequestBody DbDataSource vo) throws Exception {
        this.dbInformationSchemaService.testConncet(vo);
        return ApiResult.SUCCESS;
    }


    @ApiOperation("生成代码")
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "身份令牌", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "signature", value = "签名", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "timestamp", value = "时间戳", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "source", value = "来源（app/web/minotor）", paramType = "header", required = false, dataType = "String"), @ApiImplicitParam(name = "version", value = "版本号（1.0.0）", paramType = "header", dataType = "String")})
    @PostMapping({"/generate_code"})
    public ApiResult<GenerateCodeDto> generateCode(HttpServletResponse response, @Valid @RequestBody GenerateCodeVo vo) throws Exception {
        GenerateCodeDto res = this.dbInformationSchemaService.generateCode(vo);


        return new ApiResult(res);
    }
}


